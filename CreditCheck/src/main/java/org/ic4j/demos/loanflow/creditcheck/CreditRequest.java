package org.ic4j.demos.loanflow.creditcheck;

import java.math.BigInteger;

import org.ic4j.candid.annotations.Field;
import org.ic4j.candid.annotations.Name;
import org.ic4j.candid.types.Type;
import org.ic4j.types.Principal;

public class CreditRequest {
    @Field(Type.PRINCIPAL)
    @Name("userid")
    public Principal userId;
    @Name("firstname")
    public String firstName;
    @Name("lastname")
    public String lastName;
    public String zipcode;
    public String ssn;
    @Field(Type.INT)
    public BigInteger created;
}
