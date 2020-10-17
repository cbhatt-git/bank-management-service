# Bank Management - User Managemnet Service

### Description

This is a Spring Boot Appication which provides APIs to create and amange user informations.


# Installation (How to run in local??)



* Application Setup

> git clone https://gitlab.com/chiranjitbhatt/bank-management-service.git
>
> Go to in the project directory `cd user-service`
>
> Change the Database connection string in `resources/application.yml` file.
>
> Build the application with `mvn clean install`
>
> Run the Application with `mvn spring-boot:run`
>
> Check the server status using actuator endpoint `http://localhost:9999/actuator/health`
--------------------------
 
# Swagger UI

* You can open the swagger APIs @ `http://localhost:3001/swagger-ui.html`

[![Screen-Shot-2020-10-14-at-1-45-50-AM.png](https://i.postimg.cc/CLR4MBcQ/Screen-Shot-2020-10-14-at-1-45-50-AM.png)](https://postimg.cc/jLrfZ50H)

# Installation (How to deploy in Azure Spring Cloud??)
//TODO
`az spring-cloud app deploy -n user-service -s bank-management -g BMS --jar-path target/user-service-0.0.1-SNAPSHOT.jar`