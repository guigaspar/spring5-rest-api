# Spring Framework 5 Rest API 

[![CircleCI](https://circleci.com/gh/guigaspar/spring5-rest-api.svg?style=shield)](https://circleci.com/gh/guigaspar/spring5-rest-api) [![codecov](https://codecov.io/gh/guigaspar/spring5-rest-api/branch/master/graph/badge.svg)](https://codecov.io/gh/guigaspar/spring5-rest-api)

### Spring Boot 2 project using Spring Security with JWT and MongoDB.

---

This is an Spring Boot project made by Guillherme Gaspar, it's one API that record Customers.
I developed this API using technologies like: 
- Spring Boot 2
- Spring Security with JWT Authentication
- MongoDB
- Docker
- Swagger/Lombok/MapStruct 

We can list, find, create, update and delete customers from the NoSQL database. For all these operations the user must be authenticated. The delete endpoint it's allowed just to the admin user. I'm using CircleCI to continuous integration and CodeCov for tests coverage. This API can run with docker or not.

You will need Java 8 and Maven 3+ on your computer. If you want to run with Docker, you'll need the docker too. Also if you want to test and modify inside an IDE, you'll need the Lombok and MapStruct installed.

### Running without Docker
* Download this project.
* Start the MongoDB service/daemon in your system on port 27017(default).
* Run project by `Application.class` or by `mvn clean install`, `java -jar target/*.jar`, or by `mvn spring-boot:run`.

### Running with Docker
* Download this project.
* Download and start the mongo docker image with the command: `docker run -p 27017:27017 -t --name=mongo mongo`.
* Build our own image(This API inside a debian docker image) with the command: `mvn clean package docker:build`.
  After this command, you should see:
  `[INFO] DOCKER> Successfully tagged guilherme/springrestapi:latest`.
* Start our docker image: `docker run --name gui-api -p 8080:8080 --link mongo:mongo -e SPRING_DATA_MONGODB_HOST=mongo guilherme/springrestapi`.

----

After start, you can check the swagger at: http://localhost:8080/swagger-ui.html. This endpoint isn't protected by the Spring Security.

You can sign up one user  doing a *POST* request at the endpoint: `http://localhost:8080/api/signup`.
```
{
 "username": "apiUser",
 "password": "123456"
}
```

You can get the token doing a *POST* request at the endpoint: `http://localhost:8080/api/auth` with the same credentials previously recorded.

With the token, you can access the resources(`http://localhost:8080/api/v1/customers`) passing it in the http header:
```
x-auth-token                                        GeneratedToken
```




