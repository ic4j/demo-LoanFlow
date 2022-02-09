import Principal "mo:base/Principal";
import Option "mo:base/Option";
import None "mo:base/None";
import Time "mo:base/Time";
import Map "mo:base/HashMap";
import Hash "mo:base/Hash";
import Array "mo:base/Array";
import Iter "mo:base/Iter";
import Debug "mo:base/Debug";

actor LoanBroker {
      // Loan Application
  public type LoanApplication = {
    id: Nat;
    firstname: Text;
    lastname: Text;
    zipcode: Text;
    ssn: Text;
    amount: Float;
    term: Nat16;
    created: Int;
  };

 // Credit Check Request
  public type CreditRequest = {
    userid: Principal;   
    firstname: Text;
    lastname: Text;
    zipcode: Text;
    ssn: Text;
    created: Int;   
  };

   // Credit Check
  public type Credit = {
    userid: Principal;   
    rating: Nat16;
    created: Int;
  };

   // Loan Offer Request
  public type LoanOfferRequest = {
    applicationid: Nat;
    amount: Float;
    term: Nat16;   
    rating: Nat16;
    zipcode: Text;
    created: Int;    
  };

   // Loan Offer
    public type LoanOffer = {
        providerid: Principal;
        providername: Text;
        applicationid: Nat;
        apr: Float;
        created: Int;
    };

    type LoanProvider = actor {
        addRequest : (LoanOfferRequest) -> ();
        getName : query () -> async ?Text;
    }; 

    type CreditCheck = actor {
        addRequest : (CreditRequest) -> ();
        getName : query () -> async ?Text;        
    };    

    let eq: (Nat, Nat) -> Bool = func(x, y) { x == y };

    let providers = Map.HashMap<Principal, LoanProvider>(0, Principal.equal, Principal.hash);
    let applications = Map.HashMap<Principal, [LoanApplication]>(0, Principal.equal, Principal.hash);
    let openApplications = Map.HashMap<Nat, LoanApplication>(0, eq, Hash.hash);
    let pendingApplications = Map.HashMap<Nat, Principal>(0, eq, Hash.hash);
    let offers = Map.HashMap<Principal, [LoanOffer]>(0, Principal.equal, Principal.hash);

    stable var counter : Nat = 0;  

    var creditProvider : ?CreditCheck = null;

    public shared query (msg) func getApplications() : async [LoanApplication] {
        return Option.get<[LoanApplication]>(applications.get(msg.caller),[]);
    }; 

    public shared query (msg) func getOffers() : async [LoanOffer] {
        return Option.get<[LoanOffer]>(offers.get(msg.caller),[])
    };    

    public shared (msg) func apply(input : LoanApplication) : async Nat {
        counter += 1;

        Debug.print("Loan Application for user " #Principal.toText(msg.caller));

        let application : LoanApplication = {
        id=counter;
        firstname = input.firstname;
        lastname = input.lastname;
        ssn = input.ssn;
        amount = input.amount;
        term = input.term;
        zipcode = input.zipcode;
        created = Time.now();
        };

        var userApplications : [LoanApplication] = Option.get<[LoanApplication]>(applications.get(msg.caller),[]);
        userApplications := Array.append(userApplications, [application]);
        applications.put(msg.caller, userApplications);
        
        openApplications.put(counter, application);
        
        let creditRequest : CreditRequest = {
        userid = msg.caller;
        firstname = application.firstname;
        lastname = application.lastname;
        ssn = application.ssn;
        zipcode = application.zipcode;
        created = Time.now();
        };

        switch creditProvider {
            case null {Debug.print("Undefined Credit Check Provider");};
            case (?value) { value.addRequest(creditRequest) };
        };

        return counter;
    }; 

    public func addProvider(provider : LoanProvider) {
        var providerId = Principal.fromActor(provider);
        providers.put(providerId,provider);
    }; 

    public func setCreditCheck( provider : CreditCheck) {
        var providerId = Principal.fromActor(provider);
        Debug.print("Credit Check Provider " # Principal.toText(providerId));
        creditProvider := ?provider;
    };  

    public func setCredit(providerId : Principal, credit : Credit) {
        Debug.print("Credit for user " #Principal.toText(credit.userid));

        var userApplications : [LoanApplication] = Option.get<[LoanApplication]>(applications.get(credit.userid),[]);

        for(application in Iter.fromArray<LoanApplication>(userApplications))
        {
            if (openApplications.get(application.id) != null) 
            {
                let offerRequest : LoanOfferRequest = {
                    userid = credit.userid;
                    applicationid = application.id;
                    amount = application.amount;
                    term = application.term;
                    rating = credit.rating;
                    zipcode = application.zipcode;
                    created = application.created;
                };
                
                for (provider in providers.vals()) {
                    provider.addRequest(offerRequest);
                }; 

                pendingApplications.put(application.id, credit.userid);
                openApplications.delete(application.id);
            };
        }; 
    
    };         
    
    public shared func addOffer(providerId : Principal, offer : LoanOffer){
        switch (pendingApplications.get(offer.applicationid)) {
            case null {Debug.print("Cannot Find Application ");};
            case (?userId) { 
            Debug.print("Loan Offer for user " #Principal.toText(userId));

            var userOffers : [LoanOffer] = Option.get<[LoanOffer]>(offers.get(userId),[]);
            userOffers := Array.append(userOffers, [offer]);
            offers.put(userId, userOffers);
                };
        };     
    };        
};
