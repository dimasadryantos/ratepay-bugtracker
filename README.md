# 🚀 Ratepay Code Challange Bug Tracker 🚀

## 🧑‍💻 🤖 About the challenge
```
Implement a simple bug tracker as a REST service.

Please implement a bug tracker service before the interview :
- The restful API should support the standard operations like create, retrieve and update a simple bug ticket.
- The service should be implemented in Java and make use of Spring Boot and probably some other Spring modules.
- You may use a database of your choice (in-memory data store is allowed)
- We like testing, so please also provide appropriate tests using JUnit and Mockito.

```

## 🏗 Here are some API you need to know :

```
🚀 Create : 

Endpoint =  POST  /v1/issues
Response code = 201 created 

	{
	"issueType" : "BUG",
	"summary"  : "Hot Fix Production",
	"description" : "User not able to do transaction"
	}


🚀 Retrieve :

Endpoint = GET /v1/issues
Response code = 200 ok 
NoDataFound = code 404

   {

   	"issues": 
			[
			   	{
				"issueId" : "1",
				"issueType" : "BUG",
				"summary": "hot fix production",
				"description":"Please fix this issue",
				},

				{
				"issueId" : "2",
				"issueType" : "TASK",
				"summary": "Create module A",
				"description":"Create service A",
				}
	              ]
   }

🚀 Update

Endpoint = PUT /v1/issues/
Response code = 200 ok


				{
				"issueId" : "2",
				"issueType" : "TASK",
				"summary": "Create module A",
				"description":"Create service A",
				}

```

## 🏃👾 To run the Application

First, you may need to configure this:

```
JAVA_HOME=/Library/Java/JavaVirtualMachines/{your jdk 11 here}/Contents/Home/ 

For mac you can install sdkman

Install docker go to : https://www.docker.com

(Don't forget to setup you IDE java version if you use intellij go to file -> project structure -> project -> java version to java 11)

Environtment version i used :
java: 11.0.13-librca
maven: 3.6.3


DB Credential Set up : 
POSTGRES_USER: dimas
POSTGRES_PASSWORD: password
POSTGRES_DB: ratepay

```

##  🦾 Here are step by step to Start test the application :
```
1.Run 'docker compose up' in directory where docker-compose.yaml file located

2.Set up DB connection with credentials above and run all test

3.Run mvn install -DskipTests in project directory cd workmotion/ (without test)

4.to run springboot app Go to project and cd to target folder then execute application jar : java -jar hrapplication-0.0.1-SNAPSHOT.jar(result build from mvn install above)

5.to test endpoint use postman

```



## 🙌🏻 This project using [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

* [Core](/core) Module to store business logic and abstraction to manipulate the data.
* [Persistence](/persistence) Module to implement persistence abstraction given in Core.
* [Service](/service) Main application which orchestrate how components interact to fulfill main objective.
* [Client](/client) request response interaction for REST


## 🧑‍🔬 Why Clean Architecture  :
- 🚀 Scalability
- 🚀 Maintainability 
- 🚀 Clean code and follow SOLID Principle
- 🦾 to compose design hierarchy so the application will be separated into several modules, enhancing independence,easy to test and modularity. 


### 🏗 Infrastructure

In this project You will:

- **🧮 Unit And Integration test:** all layer covered , with 90 to 100% coverages


- **🧮 Configuration:** all beans creation.


- **🕹 Controllers(MVCP):** one per use case, why? because Easy to test and mock and clean dependency injection.

- *🚑 Postgre:** with employee table has been created 

-  🚑 Informative error exceptions

- **🗂 Docker:** to compose postgre



Thank you!
Kind Regards , Dimas Adriyanto s

