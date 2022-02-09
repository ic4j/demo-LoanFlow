export const idlFactory = ({ IDL }) => {
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
    'getRequests' : IDL.Func([], [IDL.Vec(CreditRequest)], ['query']),
    'init' : IDL.Func([IDL.Text], [], ['oneway']),
    'setCredit' : IDL.Func([IDL.Principal, IDL.Nat16], [], ['oneway']),
  });
  return CreditCheck;
};
export const init = ({ IDL }) => { return []; };
