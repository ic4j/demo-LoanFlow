import Principal "mo:base/Principal";
import Buffer "mo:base/Buffer";
import Option "mo:base/Option";
import Time "mo:base/Time";
import LoanBroker "canister:LoanBroker";

actor class LoanProvider() = this {
    var name : ?Text = null;
    type LoanOfferRequest = LoanBroker.LoanOfferRequest;
    type LoanOffer = LoanBroker.LoanOffer;

    var requests : Buffer.Buffer<LoanOfferRequest> = Buffer.Buffer(0);
    var offers : Buffer.Buffer<LoanOffer> = Buffer.Buffer(0);

    public shared (msg) func init(input : Text) {
        name := Option.make(input);
        requests := Buffer.Buffer(0);
        offers := Buffer.Buffer(0);
        LoanBroker.addProvider(this);
    };

    public shared query func getName() : async ?Text{
        return name;
    };    

    public func addRequest(request : LoanOfferRequest){
        requests.add(request);
    };

    public shared (msg) func addOffer(applicationId : Nat, apr : Float){
        let offer  : LoanOffer = {
            providerid = Principal.fromActor(this);
            providername = Option.get<Text>(name, "Loan Provider");
            applicationid = applicationId;
            apr = apr;
            created = Time.now();
        };

        offers.add(offer);
        LoanBroker.addOffer(Principal.fromActor(this),offer);
    };  

    public shared query (msg) func getRequests() : async [LoanOfferRequest] {
        return requests.toArray();
    }; 

    public shared query (msg) func getOffers() : async [LoanOffer] {
        return offers.toArray();
    };    

};