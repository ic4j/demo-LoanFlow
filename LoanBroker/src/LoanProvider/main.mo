import Principal "mo:base/Principal";
import Option "mo:base/Option";
import Time "mo:base/Time";
import Array "mo:base/Array";
import LoanBroker "canister:LoanBroker";

actor class LoanProvider() = this {
    var name : ?Text = null;
    type LoanOfferRequest = LoanBroker.LoanOfferRequest;
    type LoanOffer = LoanBroker.LoanOffer;

    var requests : [LoanOfferRequest] = [];
    var offers : [LoanOffer] = [];

    public shared (msg) func init(input : Text) {
        name := Option.make(input);
        requests := [];
        LoanBroker.addProvider(this);
    };

    public shared query func getName() : async ?Text{
        return name;
    };    

    public func addRequest(request : LoanOfferRequest){
        requests := Array.append(requests, [request]);
    };

    public shared (msg) func addOffer(applicationId : Nat, apr : Float){
        let offer  : LoanOffer = {
            providerid = Principal.fromActor(this);
            providername = Option.get<Text>(name, "Loan Provider");
            applicationid = applicationId;
            apr = apr;
            created = Time.now();
        };

        offers := Array.append(offers, [offer]);
        LoanBroker.addOffer(Principal.fromActor(this),offer);
    };  

    public shared query (msg) func getRequests() : async [LoanOfferRequest] {
        return requests;
    }; 

    public shared query (msg) func getOffers() : async [LoanOffer] {
        return offers;
    };    

};