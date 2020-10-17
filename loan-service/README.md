# Bank Management - Loan Service

### Description

This is a Spring Boot Appication which enables users to apply & manage loans. 


# Installation (How to run in local??)

* Database Setup

You should have a local instance of MySql server running. Use the below sql to setup the database before running the app.

```
CREATE SCHEMA `loan-service` ;
    
CREATE  TABLE `loan-service`.loan_data (
    LOAN_ID BIGINT AUTO_INCREMENT  NOT NULL ,
    USERNAME VARCHAR(25) NOT NULL,
    LOAN_TYPE VARCHAR(25) NOT NULL,
    LOAN_AMOUNT DECIMAL,
    LOAN_DATE DATE NOT NULL,
    RATE_OF_INTEREST DECIMAL,
    LOAN_DURATION INT,
    CREATED_ON TIMESTAMP NOT NULL,
    LAST_UPDATED_ON TIMESTAMP,
    PRIMARY KEY ( LOAN_ID )
);
```


* Application Setup (How to run in local?)

> git clone https://gitlab.com/chiranjitbhatt/bank-management-service.git
>
> Go to in the project directory `cd loan-service`
>
> Change the Database connection string in `resources/application.yml` file.
>
> Build the application with `mvn clean install`
>
> Run the Application with `mvn spring-boot:run`
>
> Check the server status using actuator endpoint `http://localhost:3002/actuator/health`
--------------------------
 
# How to access the end-points?

Once the app is up and running you can open your browser and go to the below URL for swagger UI. 

* You can open the swagger APIs @ `http://localhost:3002/swagger-ui.html`

**__**
[![Screen-Shot-2020-10-14-at-12-06-06-AM.png](https://i.postimg.cc/0jqJ9F3Z/Screen-Shot-2020-10-14-at-12-06-06-AM.png)](https://postimg.cc/47B35LXh)

Note: All the enpoints are secured with OAuth2, to create a access token you can refer to [here](https://gitlab.com/chiranjitbhatt/bank-management-service/-/blob/master/auth-server/README.md)