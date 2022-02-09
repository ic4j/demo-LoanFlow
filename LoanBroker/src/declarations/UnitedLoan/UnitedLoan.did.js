export const idlFactory = ({ IDL }) => {
  const LoanOfferRequest = IDL.Record({
    'created' : IDL.Int,
    'applicationid' : IDL.Nat,
    'term' : IDL.Nat16,
    'zipcode' : IDL.Text,
    'rating' : IDL.Nat16,
    'amount' : IDL.Float64,
  });
  const LoanOffer = IDL.Record({
    'apr' : IDL.Float64,
    'created' : IDL.Int,
    'applicationid' : IDL.Nat,
    'providername' : IDL.Text,
    'providerid' : IDL.Principal,
  });
  const LoanProvider = IDL.Service({
    'addOffer' : IDL.Func([IDL.Nat, IDL.Float64], [], ['oneway']),
    'addRequest' : IDL.Func([LoanOfferRequest], [], ['oneway']),
    'getName' : IDL.Func([], [IDL.Opt(IDL.Text)], ['query']),
    'getOffers' : IDL.Func([], [IDL.Vec(LoanOffer)], ['query']),
    'getRequests' : IDL.Func([], [IDL.Vec(LoanOfferRequest)], ['query']),
    'init' : IDL.Func([IDL.Text], [], ['oneway']),
  });
  return LoanProvider;
};
export const init = ({ IDL }) => { return []; };
