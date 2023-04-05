package org.ic4j.demos.loanflow.creditcheck;

import org.ic4j.agent.annotations.Argument;
import org.ic4j.agent.annotations.QUERY;
import org.ic4j.agent.annotations.UPDATE;
import org.ic4j.candid.types.Type;
import org.ic4j.types.Principal;

public interface CreditService {
    @QUERY
    public CreditRequest[] getRequests(); 
    
    @UPDATE
    public void setCredit(Principal userId, @Argument(Type.NAT16) Short rating);
}
