export const idlFactory = ({ IDL }) => {
  const LoanOffer = IDL.Record({
    'apr' : IDL.Float64,
    'created' : IDL.Int,
    'applicationid' : IDL.Nat,
    'providername' : IDL.Text,
    'providerid' : IDL.Principal,
  });
  const LoanOfferRequest = IDL.Record({
    'created' : IDL.Int,
    'applicationid' : IDL.Nat,
    'term' : IDL.Nat16,
    'zipcode' : IDL.Text,
    'rating' : IDL.Nat16,
    'amount' : IDL.Float64,
  });
  const LoanProvider = IDL.Service({
    'addRequest' : IDL.Func([LoanOfferRequest], [], ['oneway']),
    'getName' : IDL.Func([], [IDL.Opt(IDL.Text)], ['query']),
  });
  const LoanApplication = IDL.Record({
    'id' : IDL.Nat,
    'ssn' : IDL.Text,
    'created' : IDL.Int,
    'firstname' : IDL.Text,
    'term' : IDL.Nat16,
    'zipcode' : IDL.Text,
    'amount' : IDL.Float64,
    'lastname' : IDL.Text,
  });
  const Credit = IDL.Record({
    'created' : IDL.Int,
    'userid' : IDL.Principal,
    'rating' : IDL.Nat16,
  });
  const CreditRequest = IDL.Record({
    'ssn' : IDL.Text,
    'created' : IDL.Int,
    'firstname' : IDL.Text,
    'userid' : IDL.Principal,
    'zipcode' : IDL.Text,
    'lastname' : IDL.Text,
  });
  const CreditCheck = IDL.Service({
    'addRequest' : IDL.Func([CreditRequest], [], ['oneway']),
    'getName' : IDL.Func([], [IDL.Opt(IDL.Text)], ['query']),
  });
  return IDL.Service({
    'addOffer' : IDL.Func([IDL.Principal, LoanOffer], [], ['oneway']),
    'addProvider' : IDL.Func([LoanProvider], [], ['oneway']),
    'apply' : IDL.Func([LoanApplication], [IDL.Nat], []),
    'getApplications' : IDL.Func([], [IDL.Vec(LoanApplication)], ['query']),
    'getOffers' : IDL.Func([], [IDL.Vec(LoanOffer)], ['query']),
    'setCredit' : IDL.Func([IDL.Principal, Credit], [], ['oneway']),
    'setCreditCheck' : IDL.Func([CreditCheck], [], ['oneway']),
  });
};
export const init = ({ IDL }) => { return []; };
