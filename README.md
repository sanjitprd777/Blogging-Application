# Blogging-Application
### The is a blogging application which allows creation of user, post, comment, tag user, and more.

Build real time Rest APIs for Blogging Application
Spring Boot
String Security
JWT
Spring Data JPA (Hibernate)
MySQL

What we will learn?
Creating Rest Endpoints
Complex Db Structure (JPA Entities)
Role Based Authentication (RBAC)
Handling Exceptions
Using DTO (Data Transaction Object) for Data Transfer
Swagger
How to add profiles for diff environments.
How to deploy spring boot in productions.

Prerequisite
Core Java
OOPs
Package
Exception
Lambda
Stream API

Spring Framework
Spring Core DI
JPA
MVC

Spring Boot Basics

Basic of MySQL Database

REST
Representational (Format | Json, XML)
State (Data)
Transfer (Between two parties)

REST Guidelines
Client Server Architecture: Client & Server should be totally independent. No dependencies in REST APIs.
Stateless: No data/state stored in server of client. The client might store data on its app.
Cacheable: Once client request on server, client can store that data and reuse on new requests.
Layered System: More layer provides more scalable and load balancing
Uniform Interface: Unified interaction type between client and server.
Code on Demand (Optional): Save client code on server and send code to client when full request using this code.

Resource (On which CRUD operation is done)
Anything we want to expose to outside world through our application.

Sub-resource
Resource which is part of another main resource. Sub-resource does not exist without resource.

URI (Uniform Resource Identifier)
Using the URI to identify a resource on server

HTTP Methods
HTTP defines a set of request method to indicate the desired action to be performed for a given resource.
GET, PUT, POST, DELETE

Http Response Code
Code indicate whether a specific HTTP request has been successfully completed or some error occurred.


What are we going to build: Client Requirement

Client wants bloggin application where he/she can write blogs and articles.

We have to build simple Blogging Application:
1. User should create, update, delete, list posts.
2. User should app, update, delete, comment on posts.
3. Categories the posts according to categories.
4. New user should be able to Register to app.
5. User should able to login to our app.
6. Post include one picture too.

Proper Post, Comment, Login, Register APIs.
Post API includes Pagination and Sorting.
Proper user input validation handling.
Proper exception handling.
Robe based authentication-role based security with APIs.
JWT based authentication.
JWT all rest apis so that consumer can easy understand.
Deploy the backend application on any cloud platform.

Framework: Spring Boot Java Framework.
Java 8+, Maven, Apache Tomcat, Spring core, Spring security (jwt),
Spring data JPA (Hibernate), etc.

MySQL Database.
Postman Rest Client (API Testing).
Swagger (API Documentation).
AWS EC2 Deploy.


Architecture
Resource of our application:
USER: Login/Logout.
CATEGORY: Type of post.
POST: Main post.
COMMENT: comment on post.
TAG: Tag user on post.


Best architecture while using Spring Boot for backend.
Layered architecture

Postman (Client) --(req)--> Controllers (API Layer) ----> Services (Business logic) ----> Repositories (DAO/Persistent layer) ----> Database

ER Diagram:
USER
user_id (PK)
email
username
password
phone
about


POST
post_id (PK)
title
content
image
user_id (FK) (1:N)
