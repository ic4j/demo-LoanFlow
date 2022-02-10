# LoanFlow
Demo demonstrating various integration scenarios between the IC and Java using IC4J library

The process consists of 3 canisters, orchestrating smart contracts of 3 parties 
(Loan Client, Credit Check Agency and multiple competing Loan Providers). 

<img src="LoanBroker/canisters.png"
     alt="Canisters"
     width="500" />

For Loan Client we developed native Java Android application where the user can apply for a loan, review pending loan applications and offers. 
The application calls methods in LoanBroker Motoko canister to insert and query data.

<img src="LoanClient/apply.png"
     alt="Apply for Loan"
     width="200" /><img src="LoanClient/applications.png"
     alt="Applications"
     width="200" /> 

Loan Broker canister then orchestrates loan process, first sends request to CreditCheck canister to assign credit rating to the application. Credit check is processed by Java batch process that retrieves simulated credit data from JDBC database.


After the rating is assigned, Loan Broker application forwards the offer request to multiple subscribed Loan Provider canisters. Requests there are processed in Java Spring/Angular based  dashboard application , where loan agents can review requests and assign APR. 
  
<img src="LoanProvider/requests.png"
     alt="Loan Requests"
     width="500" />      

<img src="LoanProvider/add.png"
     alt="Add Offer"
     width="500" /> 

<img src="LoanProvider/offers.png"
     alt="Loan Offers"
     width="500" />  

The client can then review the offer again in the Android app.     

<img src="LoanClient/offers.png"
     alt="Loan Offers"
     width="200" />          

