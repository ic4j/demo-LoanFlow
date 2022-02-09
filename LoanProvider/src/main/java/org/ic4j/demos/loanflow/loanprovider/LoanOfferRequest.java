package org.ic4j.demos.loanflow.loanprovider;

import java.math.BigInteger;
import org.ic4j.candid.annotations.Name;
import org.ic4j.candid.types.Type;
import org.ic4j.candid.annotations.Field;

public class LoanOfferRequest{
    @Field(Type.NAT)
    @Name("applicationid")
    public BigInteger applicationId;
    public Double amount;
    @Field(Type.NAT16)
    public Short term;
    @Field(Type.NAT16)
    public Short rating;
    public String zipcode;   
    @Field(Type.INT) 
    public Long created;
}