import type { Principal } from '@dfinity/principal';
export interface LoanOffer {
  'apr' : number,
  'created' : bigint,
  'applicationid' : bigint,
  'providername' : string,
  'providerid' : Principal,
}
export interface LoanOfferRequest {
  'created' : bigint,
  'applicationid' : bigint,
  'term' : number,
  'zipcode' : string,
  'rating' : number,
  'amount' : number,
}
export interface LoanProvider {
  'addOffer' : (arg_0: bigint, arg_1: number) => Promise<undefined>,
  'addRequest' : (arg_0: LoanOfferRequest) => Promise<undefined>,
  'getName' : () => Promise<[] | [string]>,
  'getOffers' : () => Promise<Array<LoanOffer>>,
  'getRequests' : () => Promise<Array<LoanOfferRequest>>,
  'init' : (arg_0: string) => Promise<undefined>,
}
export interface _SERVICE extends LoanProvider {}
