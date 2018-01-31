# hello-world
This is a sample project to build some REST enpoints, to fullfill some use cases

# Pre requirements to build and run the app
You should have Java 8+ and Maven(build tool) 3+ in your classpath in order to compile and run the app. Ensure to have ~/.m2/settings.xml, to download the dependent libraries.

# Steps to start the app
 1. git clone https://github.com/aditya2007/hello-world.git to your local workspace.
 2. cd hello-world, then execute ./runServer.sh. This will compile, execute unit test cases and start the hello-world spring boot server.
 3. Copy and paste this URL http://localhost:8080/v1/hello to browser, and you should see "Hello World!". This confirms that your app is up an drunning. Ready for test.
 
 4. Now execute below curl commands, for each REST endpoint
 
   # 4.1 Hello World REST endpoint
   curl -X GET \
  'http://localhost:8080/v1/hello?saySomeThing=Yoga' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json'
  
  Response : Hello Yoga
  
  # 4.2 Create Unique Words REST endpoint
  curl -X POST \
  'http://localhost:8080/v1/uniqueWords' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
	"paragraph" : "XYZ is a brand of ABC Cable Communications,LLC, a subsidiary of the ABC Corporation, used to market consumer cable television, internet, telephone, and wireless services provided by the company.ABC company always works towards consumer."
}'

Response : [{"a":2},{"abc":1},{"always":1},{"and":1},{"brand":1},{"by":1},{"cable":2},{"communications":1},{"company":2},{"consumer":2},{"corporation":1},{"internet":1},{"is":1},{"llc":1},{"market":1},{"of":2},{"provided":1},{"services":1},{"subsidiary":1},{"telephone":1},{"television":1},{"the":2},{"to":1},{"towards":1},{"used":1},{"wireless":1},{"works":1},{"xyz":1}]

  # 4.3 Generate Fibonacci Series REST endpoint
  curl -X GET \
  'http://localhost:8080/v1/fibonacci?n=5' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json'
  
  Response : [0,1,1,2,3]
  
  # 4.4 Create Thread Deadlocks REST endpoint
  curl -X POST \
  http://localhost:8080/v1/deadlock \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: text/plain'
  
  Response : This REST endpoint created a dead lock, please monitor the dead lock by visiting this endpoint.'http://localhost:8080/v1/monitor?elapsedTime=5000'
  
  # 4.5 Monitor Threads REST endpoint
   'http://localhost:8080/v1/monitor?elapsedTime=5000' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json'
  
  Response : 
  [
    {
        "threadInfo": "Deadlock detected :: \"Account2\" Id=39 BLOCKED on com.helloworld.rest.dev.concurrent.AccountTransaction@1cab18e1 owned by \"Account1\" Id=38\n\n",
        "trace": "com.helloworld.rest.dev.concurrent.AccountTransaction.transfer(AccountTransaction.java:41)com.helloworld.rest.dev.controller.HelloWorldController.lambda$createDeadLock$1(HelloWorldController.java:165)com.helloworld.rest.dev.controller.HelloWorldController$$Lambda$11/147903969.run(Unknown Source)java.lang.Thread.run(Thread.java:748)"
    },
    {
      "threadInfo": "Deadlock detected :: \"Account1\" Id=38 BLOCKED on 	com.helloworld.rest.dev.concurrent.AccountTransaction@18949fac owned by \"Account2\" Id=39\n\n",
        "trace": "com.helloworld.rest.dev.concurrent.AccountTransaction.transfer(AccountTransaction.java:41)com.helloworld.rest.dev.controller.HelloWorldController.lambda$createDeadLock$0(HelloWorldController.java:160)com.helloworld.rest.dev.controller.HelloWorldController$$Lambda$10/1697360385.run(Unknown Source)java.lang.Thread.run(Thread.java:748)"
    }
]   

# 4.6 Invoking External Service REST endpoint
curl -X GET \
  'http://localhost:8080/v1/posts?userId=1' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json'
  
  Response :
  [
    {
        "userId": 1,
        "id": 1,
        "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
        "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
    },
    {
        "userId": 1,
        "id": 2,
        "title": "qui est esse",
        "body": "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"
    }
]

# 4.7 Create customer(example for Create row in a database) REST endpoint

curl -X POST \
  http://localhost:8080/v1/customer \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
	"firstName" : "Aditya",
	"lastName" : "Yoganada",
	"enrolledServices" : [
		"Internet",
		"Cable",
		"Phone"
		]
}'

Response :
{
    "customerId": 1,
    "firstName": "Aditya",
    "lastName": "Yoganada",
    "enrolledServices": [
        "Cable",
        "Phone",
        "Internet"
    ]
}

# 4.8 Get customer(example for query row in a database) REST endpoint
curl -X GET \
  http://localhost:8080/v1/customer/1 \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json'
  
  Response :
  {
    "customerId": 1,
    "firstName": "Aditya",
    "lastName": "Yoganada",
    "enrolledServices": [
        "Cable",
        "Phone",
        "Internet"
    ]
}

# 4.9 Delete customer(example for delete row in a database) REST endpoint
curl -X DELETE \
  http://localhost:8080/v1/customer/1 \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json'
 

  
