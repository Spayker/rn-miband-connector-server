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

    `routes:
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
           sensitiveHeaders:`  

## Auth service
Authorization responsibilities are completely extracted to separate server, which grants [OAuth2 tokens](https://tools.ietf.org/html/rfc6749) for the backend resource services. Auth Server is used for user authorization as well as for secure machine-to-machine communication inside a perimeter.



