# Bank Management Service

### About the System

The client would like to develop an independent application Bank Management System (BMS) application in order to automate the process of managing the activities of bank like opening an account, transaction etc.

The following section will cover aspects related to Bank Management System.

1. Customer Registration
2. Apply Loan
3. Update Account Details


### Architecture Diagram (Functional & Infrastructure services)

[![thumbnail-image001.png](https://i.postimg.cc/85gzGg9Y/thumbnail-image001.png)](https://postimg.cc/B8N05kq5)





### Lets understand all the components of the project and know about how to run all the apps in local.


### Config server
[Spring Cloud Config](http://cloud.spring.io/spring-cloud-config/spring-cloud-config.html) is horizontally scalable centralized configuration service for distributed systems. It uses a pluggable repository layer that currently supports local storage, Git, and Subversion. 

In this project, I used the [Config Server Repository](https://gitlab.com/chiranjitbhatt/bank-management-service-config) for different profile based configurtion.

##### Client side usage
Just build Spring Boot application with `spring-cloud-starter-config` dependency, autoconfiguration will do the rest.

Now you don't need any embedded properties in your application. Just provide `bootstrap.yml` with application name and Config service url:
```yml
spring:
  application:
    name: notification-service
  cloud:
    config:
      uri: http://config:8888
      fail-fast: true
```

##### Notes
- For local development this is not mandatory to run.
- We can dynamically change the configuration by using `@RefreshScope`
- There are some limitations for dynamic refresh though. `@RefreshScope` doesn't work with `@Configuration` classes and doesn't affect `@Scheduled` methods
- `fail-fast` property means that Spring Boot application will fail startup immediately, if it cannot connect to the Config Service.







### Auth service
Authorization responsibilities are completely extracted to separate server, which grants [OAuth2 tokens](https://tools.ietf.org/html/rfc6749) for the backend resource services. Auth Server is used for user authorization as well as for secure machine-to-machine communication inside a perimeter.

In this project, I use [`Password credentials`](https://tools.ietf.org/html/rfc6749#section-4.3) grant type for users authorization and [`Client Credentials`](https://tools.ietf.org/html/rfc6749#section-4.4) grant for microservices authorization.

Spring Cloud Security provides convenient annotations and autoconfiguration to make this really easy to implement from both server and client side. You can learn more about it in [documentation](http://cloud.spring.io/spring-cloud-security/spring-cloud-security.html) and check configuration details in [Auth Server code](https://github.com/sqshq/PiggyMetrics/tree/master/auth-service/src/main/java/com/piggymetrics/auth).

From the client side, everything works exactly the same as with traditional session-based authorization. You can retrieve `Principal` object from request, check user's roles and other stuff with expression-based access control and `@PreAuthorize` annotation.

##### Notes
- Please go [here](https://gitlab.com/chiranjitbhatt/bank-management-service/-/blob/master/auth-server/README.md) to know more about how to run the app in local.







### API Gateway
As you can see, there are three core services, which expose external API to client. In a real-world systems, this number can grow very quickly as well as whole system complexity. Actually, hundreds of services might be involved in rendering of one complex webpage.

In theory, a client could make requests to each of the microservices directly. But obviously, there are challenges and limitations with this option, like necessity to know all endpoints addresses, perform http request for each piece of information separately, merge the result on a client side. Another problem is non web-friendly protocols which might be used on the backend.

Usually a much better approach is to use API Gateway. It is a single entry point into the system, used to handle requests by routing them to the appropriate backend service or by invoking multiple backend services and [aggregating the results](http://techblog.netflix.com/2013/01/optimizing-netflix-api.html). Also, it can be used for authentication, insights, stress and canary testing, service migration, static response handling, active traffic management.

Netflix opensourced [such an edge service](http://techblog.netflix.com/2013/06/announcing-zuul-edge-service-in-cloud.html), and now with Spring Cloud we can enable it with one `@EnableZuulProxy` annotation. In this project, I use Zuul to store static content (ui application) and to route requests to appropriate microservices. Here's a simple prefix-based routing configuration for Notification service:

##### Notes
- Please go [here](https://gitlab.com/chiranjitbhatt/bank-management-service/-/blob/master/api-gateway/README.md) to know more about how to run the app in local.




### Service discovery

Another commonly known architecture pattern is Service discovery. It allows automatic detection of network locations for service instances, which could have dynamically assigned addresses because of auto-scaling, failures and upgrades.

The key part of Service discovery is Registry. I use Netflix Eureka in this project. Eureka is a good example of the client-side discovery pattern, when client is responsible for determining locations of available service instances (using Registry server) and load balancing requests across them.

With Spring Boot, you can easily build Eureka Registry with `spring-cloud-starter-eureka-server` dependency, `@EnableEurekaServer` annotation and simple configuration properties.


Now, on application startup, it will register with Eureka Server and provide meta-data, such as host and port, health indicator URL, home page etc. Eureka receives heartbeat messages from each instance belonging to a service. If the heartbeat fails over a configurable timetable, the instance will be removed from the registry.

Also, Eureka provides a simple interface, where you can track running services and a number of available instances: `http://localhost:8761`

##### Notes
- Please go [here](https://gitlab.com/chiranjitbhatt/bank-management-service/-/blob/master/service-discovery/README.md) to know more about how to run the app in local.



### User Service
This Service enables user to register and manage user accounts.

##### Notes
- Please go [here](https://gitlab.com/chiranjitbhatt/bank-management-service/-/blob/master/user-service/README.md) to know more about how to run the app in local.


### Loan Service
This service enable users to apply and mamnage loans. 

##### Notes
- Please go [here](https://gitlab.com/chiranjitbhatt/bank-management-service/-/blob/master/loan-service/README.md) to know more about how to run the app in local.


### CodeMR Reports

[![Screen-Shot-2020-10-20-at-4-35-18-PM.png](https://i.postimg.cc/zGqsgTtz/Screen-Shot-2020-10-20-at-4-35-18-PM.png)](https://postimg.cc/GHSg0TnV)
[![Screen-Shot-2020-10-20-at-4-34-57-PM.png](https://i.postimg.cc/vB5hhNTp/Screen-Shot-2020-10-20-at-4-34-57-PM.png)](https://postimg.cc/R6VwVGcR)


# How To Deploy in Azure Spring Cloud?

* Create a MySql database service using Azure management console.
* Connect the DB from local and execute the sql scripts mentioned in Loan-Service & Auth-Server. (README.md)
* Create a Azure Spring Cloud Service using management console.  [How To Create?](https://docs.microsoft.com/en-us/azure/spring-cloud/spring-cloud-quickstart?tabs=Azure-CLI&pivots=programming-language-java)
* Once the Service has been created, we have to configure the Config-Server. We are using the app-config directory in this repository for storing our app configurations. [How to configure?](https://docs.microsoft.com/en-us/azure/spring-cloud/spring-cloud-quickstart-setup-config-server?tabs=Azure-portal&pivots=programming-language-java)
* No we are ready to create our apps. We will do it by using terminal.
  >> Open the terminal in the project root directory.
  >> Execute the below commands 
    ```
    az login
    az configure --defaults group=BMS
    az configure --defaults spring-cloud=bank-management-service
    az spring-cloud app create --name api-gateway --env spring.profiles.active=cloud --is-public
    az spring-cloud app create --name auth-server --env spring.profiles.active=cloud --is-public
    az spring-cloud app create --name user-service --env spring.profiles.active=cloud
    az spring-cloud app create --name loan-service --env spring.profiles.active=cloud
    ```
    Once this commands are successfully executed you can go and check the management cosole, you will se the apps are created and running with a sample application. Here we have assigned public URl for the API-Gateway and Auth-server.
* Now we can deploy our apps manually using the below commands after building the project
  ```
    az spring-cloud app deploy -n hellospring -s <service instance name> -g <resource group name> --jar-path <jar file path>
  ```
