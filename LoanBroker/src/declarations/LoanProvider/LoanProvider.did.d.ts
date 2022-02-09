import type { Principal } from '@dfinity/principal';
export interface LoanOffer {
  'apr' : number,
  'created' : string,
  'applicationid' : bigint,
  'userid' : Principal,
  'providername' : string,
}
export interface LoanOfferRequest {
  'created' : string,
  'applicationid' : bigint,
  'userid' : Principal,
  'zipcode' : string,
  'rating' : number,
}
export interface LoanProvider {
  'addOffer' : (arg_0: LoanOffer) => Promise<undefined>,
  'addRequest' : (arg_0: LoanOfferRequest) => Promise<undefined>,
  'getRequests' : () => Promise<Array<LoanOfferRequest>>,
  'init' : () => Promise<undefined>,
}
export interface _SERVICE extends LoanProvider {}
