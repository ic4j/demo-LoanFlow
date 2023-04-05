package org.ic4j.demos.loanflow.loanclient;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;

import org.ic4j.agent.annotations.QUERY;
import org.ic4j.agent.annotations.UPDATE;

public interface LoanBroker {
    @QUERY
    public LoanApplication[] getApplications();

    @QUERY
    public LoanOffer[] getOffers();

    @UPDATE
    public CompletableFuture<BigInteger> apply(LoanApplication application);
}
