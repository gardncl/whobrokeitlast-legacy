# Who Broke it Last 
[![Build Status](https://travis-ci.org/gardncl/whobrokeitlast.svg?branch=master)](https://travis-ci.org/gardncl/whobrokeitlast) [![codecov](https://codecov.io/gh/gardncl/whobrokeitlast/branch/master/graph/badge.svg)](https://codecov.io/gh/gardncl/whobrokeitlast) [![bitHound Code](https://www.bithound.io/github/gardncl/whobrokeitlast/badges/code.svg)](https://www.bithound.io/github/gardncl/whobrokeitlast)  
Application for tracking who has broken the current development/test/production build last--aka BSaaS (Build Shaming as a Service)--in a continuous integration environment. Technical overview: Front end OAuth2 authentication for maintaining session. Back end is a stateless, RESTful API that authenticates using JWT. The broken build will be updated either through the GUI or a webhook.

## Stack:  
* MySQL (AWS RDS)  
* Spring Boot  
* Java   
* Angular

## Tools:  
* Gradle  
* Travis CI  
* NPM
* AngularCLI


## Usage

Select which projects you would like to track
![alt text](./project-list.png "Project Page")

Add the webhook address to the travis.yml
![alt text](./travis-yml.png "Travis-CI")



Push a broken build and see who broke it last!
