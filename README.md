# hello-world
This is a sample project to build some REST enpoints, to fullfill some use cases

Project Structure
==================
├── README.md
├── hello-world.iml
├── pom.xml
├── runServer.sh
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── helloworld
│   │   │           └── rest
│   │   │               └── dev
│   │   │                   ├── Application.java
│   │   │                   ├── concurrent
│   │   │                   │   ├── AccountTransaction.java
│   │   │                   │   └── ThreadMonitor.java
│   │   │                   ├── config
│   │   │                   │   └── HelloworldPersistenceConfig.java
│   │   │                   ├── controller
│   │   │                   │   ├── CustomerController.java
│   │   │                   │   └── HelloWorldController.java
│   │   │                   ├── dto
│   │   │                   │   ├── Customer.java
│   │   │                   │   ├── Post.java
│   │   │                   │   ├── ThreadSummary.java
│   │   │                   │   └── Word.java
│   │   │                   ├── helper
│   │   │                   │   └── HelloWorldHelper.java
│   │   │                   └── persistence
│   │   │                       ├── entities
│   │   │                       │   ├── BaseEntity.java
│   │   │                       │   ├── CustomerEntity.java
│   │   │                       │   └── EnrolledServiceEntity.java
│   │   │                       ├── repository
│   │   │                       │   └── CustomerRepository.java
│   │   │                       └── service
│   │   │                           ├── CustomerService.java
│   │   │                           ├── CustomerServiceImpl.java
│   │   │                           ├── ExternalService.java
│   │   │                           └── ExternalServiceImpl.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── jpa-persistence.properties
│   └── test
│       ├── java
│       │   └── com
│       │       └── helloworld
│       │           └── rest
│       │               └── dev
│       │                   ├── controller
│       │                   │   ├── AbstractControllerTest.java
│       │                   │   ├── CustomerControllerTest.java
│       │                   │   └── HelloWordControllerTest.java
│       │                   ├── helper
│       │                   │   └── HelloWorldHelperTest.java
│       │                   └── persistence
│       │                       └── service
│       │                           ├── CustomerServiceTest.java
│       │                           └── TestApp.java
│       └── resources
│           ├── json
│           │   ├── input
│           │   │   └── uniquewords.json
│           │   └── output
│           │       └── uniquewords.json
│           └── test-jpa-persistence.properties

