import type { Principal } from '@dfinity/principal';
export interface CreditCheck {
  'addRequest' : (arg_0: CreditRequest) => Promise<undefined>,
  'getName' : () => Promise<[] | [string]>,
  'getRequests' : () => Promise<Array<CreditRequest>>,
  'init' : (arg_0: string) => Promise<undefined>,
  'setCredit' : (arg_0: Principal, arg_1: number) => Promise<undefined>,
}
export interface CreditRequest {
  'ssn' : string,
  'created' : bigint,
  'firstname' : string,
  'userid' : Principal,
  'zipcode' : string,
  'lastname' : string,
}
export interface _SERVICE extends CreditCheck {}
