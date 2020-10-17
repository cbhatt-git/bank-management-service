# Bank Management - Authentication Server

### Description

This is a Spring Boot Appication which provides authentication using **OAuth2** 

Note: User credentials are maintained in MySql database.

# Installation (How to run in local??)

* Database Setup

You should have a local instance of MySql server running. Use the below sql to setup the database and create a dummy user for testing purpose.

```
CREATE SCHEMA `user-service` ;

CREATE  TABLE `user-service`.user (
    ID BIGINT AUTO_INCREMENT  NOT NULL ,
    ACCOUNT_TYPE VARCHAR(255),
    ADDRESS VARCHAR(255),
    CONTACT_NUMBER VARCHAR(255),
    COUNTRY VARCHAR(255),
    DATE_OF_BIRTH DATE,
    EMAIL VARCHAR(255) NOT NULL,
    IS_ACCOUNT_EXPIRED BOOLEAN NOT NULL,
    IS_ACCOUNT_LOCKED BOOLEAN NOT NULL,
    IS_ACTIVE BOOLEAN NOT NULL,
    IS_CREENTIALS_EXPIRED BOOLEAN NOT NULL,
    NAME VARCHAR(255) NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    PERMANENT_ACCOUNT_NUMBER VARCHAR(255),
    STATE VARCHAR(255),
    USERNAME VARCHAR(255) NOT NULL,
    CREATED_ON TIMESTAMP NOT NULL,
    LAST_UPDATED_ON TIMESTAMP,
    PRIMARY KEY ( id )
);

INSERT INTO `user-service`.user 
(NAME, EMAIL, USERNAME, PASSWORD,  IS_ACCOUNT_EXPIRED, IS_ACCOUNT_LOCKED, IS_ACTIVE, IS_CREENTIALS_EXPIRED) 
VALUES 
('ADMIN', 'admin@cognizant.com', 'admin', '$2a$10$oPKrD9fL1sV6C/mgvDysO.4VCIx6gZSY9sfVaf0cmgsP4t0XRMwVu', false, false, true, false);
```


* Application Setup

> git clone https://gitlab.com/chiranjitbhatt/bank-management-service.git
>
> Go to in the project directory `cd service-discovery`
>
> Change the Database connection string in `resources/application.yml` file.
>
> Build the application with `mvn clean install`
>
> Run the Application with `mvn spring-boot:run`
>
> Check the server status using actuator endpoint `http://localhost:9999/actuator/health`
--------------------------
 
# How to create a OAuth2 token?

* Once the service is up and running in local use the below curl command to create a new token

```
curl --location --request POST 'http://localhost:9999/oauth/token' \
--header 'Authorization: Basic Y2xpZW50OnNlY3JldA==' \
--form 'grant_type=password' \
--form 'username=admin' \
--form 'password=secret'
```
* Also you can find a Postman Collection with the request details to create a token in **WIKI**

* You can open the swagger APIs @ `http://localhost:9999/swagger-ui.html`


az spring-cloud app deploy -n user-service -s bank-management -g BMS --jar-path target/user-service-0.0.1-SNAPSHOT.jar