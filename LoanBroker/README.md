# LoanBroker

Welcome to your new LoanBroker project and to the internet computer development community. By default, creating a new project adds this README and some template files to your project directory. You can edit these template files to customize your project and to include your own code to speed up the development cycle.

To get started, you might want to explore the project directory structure and the default configuration file. Working with this project in your development environment will not affect any production deployment or identity tokens.

To learn more before you start working with LoanBroker, see the following documentation available online:

- [Quick Start](https://sdk.dfinity.org/docs/quickstart/quickstart-intro.html)
- [SDK Developer Tools](https://sdk.dfinity.org/docs/developers-guide/sdk-guide.html)
- [Motoko Programming Language Guide](https://sdk.dfinity.org/docs/language-guide/motoko.html)
- [Motoko Language Quick Reference](https://sdk.dfinity.org/docs/language-guide/language-manual.html)


## Running the project locally

If you want to test your project locally, you can use the following commands:

```bash
# Starts the replica, running in the background
dfx start --background

# Deploys your canisters to the replica and generates your candid interface
dfx deploy
```

Which will start a server at `http://localhost:4943`, proxying API requests to the replica at port 4943.

To initialize CreditCheck canister call init function

```bash
dfx canister call CreditCheck init 'Credit Rating'
```

To initialize UnitedLoan and StarLoan loan provider canisters call init function.

```bash
dfx canister call UnitedLoan init 'United Loan'
dfx canister call StarLoan init 'Star Loan'
```

