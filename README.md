# Who Broke it Last [![CircleCI](https://circleci.com/gh/gardncl/whobrokeitlast.svg?style=shield)](https://circleci.com/gh/gardncl/whobrokeitlast)
===============================  
Application for tracking who has broken the current development/test/production build last--aka BSaaS (Build Shaming as a Service)--in a continuous integration environment. Technical overview: Front end OAuth2 authentication for maintaining session. Back end is a stateless, RESTful API that authenticates using JWT. The broken build will be updated either through the GUI or a webhook.

## Stack:  
MySQL (AWS RDS)  
Spring Boot  
Java   
AngularJS

## Tools:  
Gradle  
CircleCI
WebPack
