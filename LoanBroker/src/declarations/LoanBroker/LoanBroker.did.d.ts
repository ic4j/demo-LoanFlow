import type { Principal } from '@dfinity/principal';
export interface Credit {
  'created' : bigint,
  'userid' : Principal,
  'rating' : number,
}
export interface CreditCheck {
  'addRequest' : (arg_0: CreditRequest) => Promise<undefined>,
  'getName' : () => Promise<[] | [string]>,
}
export interface CreditRequest {
  'ssn' : string,
  'created' : bigint,
  'firstname' : string,
  'userid' : Principal,
  'zipcode' : string,
  'lastname' : string,
}
export interface LoanApplication {
  'id' : bigint,
  'ssn' : string,
  'created' : bigint,
  'firstname' : string,
  'term' : number,
  'zipcode' : string,
  'amount' : number,
  'lastname' : string,
}
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
  'addRequest' : (arg_0: LoanOfferRequest) => Promise<undefined>,
  'getName' : () => Promise<[] | [string]>,
}
export interface _SERVICE {
  'addOffer' : (arg_0: Principal, arg_1: LoanOffer) => Promise<undefined>,
  'addProvider' : (arg_0: Principal) => Promise<undefined>,
  'apply' : (arg_0: LoanApplication) => Promise<bigint>,
  'getApplications' : () => Promise<Array<LoanApplication>>,
  'getOffers' : () => Promise<Array<LoanOffer>>,
  'setCredit' : (arg_0: Principal, arg_1: Credit) => Promise<undefined>,
  'setCreditCheck' : (arg_0: Principal) => Promise<undefined>,
}
