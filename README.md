[![GitHub license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/Spayker/sbp_server/blob/master/LICENSE)    [![Build Status](https://travis-ci.org/Spayker/sbp_server.svg?branch=master)](https://travis-ci.org/Spayker/sbp_server) [![codecov.io](https://codecov.io/github/Spayker/sbp_server/coverage.svg?branch=master)](https://codecov.io/github/Spayker/sbp_server?branch=master) [![Gitter](https://badges.gitter.im/sbp_server/community.svg)](https://gitter.im/sbp_server/community?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

# SBP prototype 

**A server side for handling data from smart watches, bands**

SBP offers PoC of server side solution to work with data that comes from smart watches/bands. Based on Micro-Service approach core functionality.

**Key tech tools**
- Java 11 (openJDK)
- Spring Boot (2.1.4.RELEASE)
- Spring Cloud (Greenwich.SR4)
- MongoDB (v3)
- Docker (with Docker Compose)

As a PoC it has all important tools to maintain whole server structure including:
1) docker compose dev run scripts
2) additional Intellij Idea based run configurations for rapid access to debug mode and hot deploy feature on a correspond service container. More details are left below.  

## Client side
As a client you can use some rest client (Postman, Insomnia) or get and run a mobile solution
based on react-native framework. Please check it out [here](https://github.com/Spayker/rn-miband-connector). Couple
words about it: generally that client is able to link Xiaomi MiBand 3 device, pass pairing process and
get data from it (current heart beat, passed steps, device meta info).

## Services review

Main components of SBP are described below on scheme:
![alt text](resources/media/SBP_microservice_architecture_1.jpg)

SBP consists of three service groups:
1) business domain services (Account, Device)
2) technical services (Gateway, Auth, Config, Eureka, RabbitMq)
3) MongoDB instances to provide data persistence.

## Account service
Contains user related logic including validation. Dev port: 6000

Method	| Path	| Description	| User authenticated	
------------- | ------------------------- | ------------- |:-------------:|
GET	| /accounts/{name}	| Get specified account by his name	| * | 	
POST	| /accounts/	| Register a new account	|   

## Device service
Contains device related logic that is capable to register a new device, attach it to created account.
Last but not least it has rest end-points to handle device data and return already gathered data to client.
Default dev port: 7000

Method	| Path	| Description	| User authenticated
------------- | ------------------------- | ------------- |:-------------:|
GET	| /devices/{deviceId}	| Get device data by device id          | *	
POST	| /devices/	| persists a new device in db	|  *
PUT	| /devices/	| modifies device's record by provided device json	| *

## Gateway point
Such service provides a single entry point into the system, 
used to handle requests by routing them to the appropriate backend service 
or by invoking multiple backend services.
Default dev port: 4000

Route config (./config/src/main/resources/shared/gateway-point.yml):

     routes:
        auth-service:
           path: /mservicet/**
           url: http://auth-service:${AUTH_SERVICE_DEV_PORT}
           stripPrefix: false
           sensitiveHeaders:
 
        account-service:
           path: /accounts/**
           serviceId: account-service
           stripPrefix: false
           sensitiveHeaders:
 
        device-service:
           path: /devices/**
           serviceId: device-service
           stripPrefix: false
           sensitiveHeaders:

## Auth service
Auth service grants [OAuth2 tokens](https://tools.ietf.org/html/rfc6749) for backend services. It is used for user authorization as well as for secure machine-to-machine communication in terms of inner service circle. Default dev port: 5000

Method	| Path	| Description	| User authenticated
------------- | ------------------------- | ------------- |:-------------:|
POST	| /mservicet/oauth/token	| Get user access token          | 	

## Config service
[Config service](http://cloud.spring.io/spring-cloud-config/spring-cloud-config.html) is horizontally scalable centralized configuration service for distributed systems. Main purpose: keep and share configurations among all services the server has during start. Configs are shared by Native profile (that can be changed at any moment). Config sharing happens once all containers are setup and have begun their init procedure. Default dev port: 8888

## Eureka service
Netflix Eureka service implements "Service discovery" architecture pattern. It allows to auto detect service instances, which could have dynamically assigned addresses because of auto-scaling, failures and upgrades.

Once application startup has begun, it will register with Eureka Server and provide meta-data, such as host and port, health indicator URL, home page etc. Eureka receives heartbeat messages from each instance belonging to a service. If the heartbeat fails over a configurable timetable, the instance will be removed from the registry.

Also, Eureka provides a simple interface for tracking of running services and quantity of available instances: `http://localhost:8761`
Default dev port: 8761

## RabbitMq service
RabbitMQ is known as a “traditional” message broker, which is suitable for a wide range of projects. It is successfully used both for development of new startups and notable enterprises. Included by default into Netflix micro-service tool set.

Currently it's not used in a "full-scale" by the project. Default dev port: 5672

## MongoDB instances
Like it was mentioned before each business related service has its own db. In addition Auth service also has it to make persistence of user related data possible.

Account and Device dbs use dump scripts located at `./mongodb/dump` folder.
Default ports may vary.

Auth external db port: 25000
Account external db port: 26000
Device external db port: 27000

Typical access url example: `http://localhost:25000`

## Project deployment
Docker Compose allows to run micro-service infrastructure by 'one click'. It makes sense when a server consists of significant amount of services. Current configuration supplies next project's needs:
1) rapid and comfort deployment of appropriate services for development purposes
2) centralized access to deployment configuration
3) horizontal scaling of deployment variations: when server runs with partial set of services

In general docker compose config can be divided on few items:
- `.env`: keeps port, credential and some infrastructure configs for all project's services
- `docker-compose.yml`: enlists all services must be run by server and includes major configuration per container
- `docker-compose.dev.yml`: specifies additional configuration for services that must be run in dev mode








