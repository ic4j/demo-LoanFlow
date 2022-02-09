import Principal "mo:base/Principal";
import Option "mo:base/Option";
import Time "mo:base/Time";
import Map "mo:base/HashMap";
import Iter "mo:base/Iter";
import LoanBroker "canister:LoanBroker";

actor class CreditCheck() = this {
    var name : ?Text = null;
    type CreditRequest = LoanBroker.CreditRequest;
    type Credit = LoanBroker.Credit;

    let requests = Map.HashMap<Principal, LoanBroker.CreditRequest>(0, Principal.equal, Principal.hash);

    public shared (msg) func init(input : Text) {
        name := Option.make(input);
        LoanBroker.setCreditCheck(this);
    };

    public shared query func getName() : async ?Text{
        return name;
    };   

    public func addRequest(request : CreditRequest){
        requests.put(request.userid, request);
    };

    public shared (msg) func setCredit(userId : Principal, rating : Nat16){
        let credit : Credit = {
            userid = userId;
            rating = rating;
            created = Time.now();        
        };

        LoanBroker.setCredit(Principal.fromActor(this), credit);
        requests.delete(credit.userid);
    };

    public shared query (msg) func getRequests() : async [CreditRequest] {
        var entries = Iter.toArray(requests.vals());
        return entries;
    };    
}