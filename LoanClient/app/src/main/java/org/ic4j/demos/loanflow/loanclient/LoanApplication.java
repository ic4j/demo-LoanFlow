package org.ic4j.demos.loanflow.loanclient;

import java.math.BigInteger;

import org.ic4j.candid.annotations.Field;
import org.ic4j.candid.annotations.Name;
import org.ic4j.candid.types.Type;

public class LoanApplication {
    @Field(Type.NAT)
    @Name("id")
    public BigInteger applicationId;
    @Name("firstname")
    public String firstName;
    @Name("lastname")
    public String lastName;
    public String zipcode;
    public String ssn;
    public Double amount;
    @Field(Type.NAT16)
    public Short term;
    @Field(Type.INT)
    public Long created;
}
