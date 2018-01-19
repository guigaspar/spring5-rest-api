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
- Lombok/MapStruct 

We can list, find, create, update and delete customers from the NoSQL database. For all these operations the user must be authenticated. The delete endpoint it's allowed just to the admin user. I'm using CircleCI to continuous integration and CodeCov for tests coverage.


### Running
* Download this project
* Start the MongoDB service/daemon in your system on port 27017(default)
* Run project by `Application.class` or by `mvn clean install`, `java -jar target/*.jar`, or by `mvn spring-boot:run`
