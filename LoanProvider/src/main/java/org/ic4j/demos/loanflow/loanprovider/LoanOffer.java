package org.ic4j.demos.loanflow.loanprovider;

import java.math.BigInteger;
import org.ic4j.candid.annotations.Name;
import org.ic4j.candid.types.Type;
import org.ic4j.candid.annotations.Field;

public class LoanOffer {
    @Field(Type.NAT)
    @Name("applicationid")
    public BigInteger applicationId;
    public Double apr;
    @Field(Type.INT)
    public Long created;    
}
