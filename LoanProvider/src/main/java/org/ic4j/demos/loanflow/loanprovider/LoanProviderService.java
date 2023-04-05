package org.ic4j.demos.loanflow.loanprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.ic4j.agent.Agent;
import org.ic4j.agent.AgentBuilder;
import org.ic4j.agent.ReplicaTransport;
import org.ic4j.agent.http.ReplicaApacheHttpTransport;
import org.ic4j.types.Principal;

@Service
@Configuration
@PropertySource("classpath:application.properties")
public class LoanProviderService {
    @Autowired
    private Environment env;
    
    LoanProvider loanProvider;
    
    @PostConstruct
	void getLoanProviderService()
	{
		String icLocation = env.getProperty("ic.location");
		String icCanister = env.getProperty("ic.canister");

		ReplicaTransport transport;
		try {
			transport = ReplicaApacheHttpTransport.create(icLocation);
			Agent agent = new AgentBuilder().transport(transport).build();

			loanProvider =  org.ic4j.agent.ProxyBuilder.create(agent,Principal.fromString(icCanister)).getProxy(LoanProvider.class);
				
		} catch (URISyntaxException e) {
		}
	}

    protected String getName()
    {
        return loanProvider.getName();
    }
    
    protected List<LoanOfferRequest> getRequests() {
		List<LoanOfferRequest> requestList = Arrays.asList(loanProvider.getRequests());
		Collections.reverse(requestList);
        return requestList;
    }
    
    protected List<LoanOffer> getOffers() {
		List<LoanOffer> offerList = Arrays.asList(loanProvider.getOffers());
		Collections.reverse(offerList);
        return offerList;
    }    

    protected void addOffer(LoanOfferResponse response) {
        loanProvider.addOffer( BigInteger.valueOf(response.applicationId), response.apr);
    }     
    
}
