export const idlFactory = ({ IDL }) => {
  const LoanOffer = IDL.Record({
    'apr' : IDL.Float64,
    'created' : IDL.Text,
    'applicationid' : IDL.Nat,
    'userid' : IDL.Principal,
    'providername' : IDL.Text,
  });
  const LoanOfferRequest = IDL.Record({
    'created' : IDL.Text,
    'applicationid' : IDL.Nat,
    'userid' : IDL.Principal,
    'zipcode' : IDL.Text,
    'rating' : IDL.Nat16,
  });
  const LoanProvider = IDL.Service({
    'addOffer' : IDL.Func([LoanOffer], [], ['oneway']),
    'addRequest' : IDL.Func([LoanOfferRequest], [], ['oneway']),
    'getRequests' : IDL.Func([], [IDL.Vec(LoanOfferRequest)], ['query']),
    'init' : IDL.Func([], [], ['oneway']),
  });
  return LoanProvider;
};
export const init = ({ IDL }) => { return []; };
