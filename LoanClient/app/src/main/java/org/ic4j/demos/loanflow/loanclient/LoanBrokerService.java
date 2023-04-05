package org.ic4j.demos.loanflow.loanclient;

import android.app.Service;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import org.ic4j.agent.Agent;
import org.ic4j.agent.AgentBuilder;
import org.ic4j.agent.ProxyBuilder;
import org.ic4j.agent.ReplicaTransport;
import org.ic4j.agent.http.ReplicaOkHttpTransport;
import org.ic4j.agent.identity.BasicIdentity;
import org.ic4j.agent.identity.Identity;
import org.ic4j.types.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.security.Security;
import java.util.concurrent.CompletableFuture;

public class LoanBrokerService extends Service {
    static final Logger LOG = LoggerFactory.getLogger(LoanBrokerService.class);
    // Binder given to clients
    private final IBinder binder = new LocalBinder();

    // IC Service
    private LoanBroker loanBroker;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return this.binder;
    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        LoanBrokerService getService() {
            // Return this instance of LocalService so clients can call public methods
            return LoanBrokerService.this;
        }
    }

    @Override
    public void onCreate() {
        new Thread(new Runnable() {
            public void run() {
                String icLocation = getString(R.string.ic_location);
                String icCanister = getString(R.string.ic_canister);

                Resources res = getResources();

                Security.removeProvider("BC");

                try {
                    Reader identityReader = new BufferedReader(new InputStreamReader(res.openRawResource(R.raw.identity)));

                    Identity identity = BasicIdentity.fromPEMFile(identityReader);
                    ReplicaTransport transport = ReplicaOkHttpTransport.create(icLocation);

                    Agent agent = new AgentBuilder().transport(transport).identity(identity).build();
                    loanBroker = ProxyBuilder.create(agent, Principal.fromString(icCanister)).getProxy(LoanBroker.class);
                }catch (URISyntaxException e) {
                    LOG.error(e.getLocalizedMessage(),e);
                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
    }

    public LoanApplication[] getApplications()
    {
        if(this.loanBroker == null)
            return new LoanApplication[0];
        else
            return this.loanBroker.getApplications();
    }

    public LoanOffer[] getOffers()
    {
        if(this.loanBroker == null)
            return new LoanOffer[0];
        else
            return this.loanBroker.getOffers();
    }

    CompletableFuture<BigInteger> apply(LoanApplication application)
    {
        return this.loanBroker.apply(application);
    }
}
