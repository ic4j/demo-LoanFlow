package org.ic4j.demos.loanflow.loanclient;

import java.math.BigInteger;

import org.ic4j.candid.annotations.Field;
import org.ic4j.candid.annotations.Name;
import org.ic4j.candid.types.Type;

public class LoanOffer {
    @Field(Type.TEXT)
    @Name("providerName")
    public String providerName;
    @Field(Type.NAT)
    @Name("applicationid")
    public BigInteger applicationId;
    public Double apr;
    @Field(Type.INT)
    public Long created;
}
