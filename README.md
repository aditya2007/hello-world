# hello-world
This is a sample project to build some REST enpoints, to fullfill some use cases

# Pre requirements to build and run the app
You should have Java 8+ and Maven(build tool) 3+ in your classpath in order to compile and run the app. Ensure to have ~/.m2/settings.xml, to download the dependent libraries.

# Steps to start the app
 1. git clone https://github.com/aditya2007/hello-world.git to your local workspace.
 2. cd hello-world, then execute ./runServer.sh. This will compile, execute unit test cases and start the hello-world spring boot server.
 3. Copy and paste this URL http://localhost:8080/v1/hello to browser, and you should see "Hello World!". This confirms that your app is up an drunning.
 4. Now execute below curl commands, for each REST endpoint
   4.1 Hello World REST endpoint
   curl -X GET \
  'http://localhost:8080/v1/hello?saySomeThing=Yoga' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json'
  
  Response : Hello Yoga
  
  4.2 Create Unique Words REST endpoint
  curl -X POST \
  http://localhost:8080/v1/uniqueWords \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -d '{
	"paragraph" : "XYZ is a brand of ABC Cable Communications,LLC, a subsidiary of the ABC Corporation, used to market consumer cable television, internet, telephone, and wireless services provided by the company.ABC company always works towards consumer."
}'

Response : [{"a":2},{"abc":1},{"always":1},{"and":1},{"brand":1},{"by":1},{"cable":2},{"communications":1},{"company":2},{"consumer":2},{"corporation":1},{"internet":1},{"is":1},{"llc":1},{"market":1},{"of":2},{"provided":1},{"services":1},{"subsidiary":1},{"telephone":1},{"television":1},{"the":2},{"to":1},{"towards":1},{"used":1},{"wireless":1},{"works":1},{"xyz":1}]


