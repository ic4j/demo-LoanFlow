package org.ic4j.demos.loanflow.loanprovider;

import java.math.BigInteger;

import org.ic4j.agent.annotations.QUERY;
import org.ic4j.agent.annotations.UPDATE;
import org.ic4j.agent.annotations.Argument;
import org.ic4j.candid.types.Type;

public interface LoanProvider
{
    @QUERY
    public String getName();

    @QUERY
    public LoanOfferRequest[] getRequests();
    
    @QUERY
    public LoanOffer[] getOffers();    

    @UPDATE
    public void addOffer(@Argument(Type.NAT) BigInteger applicationId, Double apr);
}