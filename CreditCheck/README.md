# IC4J Java Loan Flow demo CreditRating batch process.


This application requires Java version 11.

Modify [application.properties](src/main/resources/application.properties) file to set canister location and id.

```
ic.location=http://127.0.0.1:4943
ic.canister=rrkah-fqaaa-aaaaa-aaaaq-cai
```

Run Gradle [build](build.gradle). Modify Java version in the build file if higher than 11.

```
gradle build
```

Run Java with fat jar

```
java -jar build/libs/ic4j-demo-creditcheck-0.6.19.jar
```
