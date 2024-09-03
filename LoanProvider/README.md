# Loan Flow demo LoanProvider Spring/Angular Dashboard Web Appliction.


This application requires Java version 11.

Modify [application.properties](src/main/resources/application.properties) file to set canister location and id.

```
ic.location=http://127.0.0.1:4943
ic.canister=rno2w-sqaaa-aaaaa-aaacq-cai
```
Build Angular client in angularclient directory.

```
cd angularclient
ng build
cd ..
```
Copy Angular distribution files to Spring resources/static directory.

```
cp -R angularclient/dist/angularclient/ src/main/resources/static
```

Run Maven [build](pom.xml). Modify Java version in the build file if higher than 11.

```
mvn package
```

Run Java with fat jar

```
java -jar target/LoanProvider-0.7.0.jar
```

In Web Browser open URL [http://locahost:8080](http://locahost:8080)
