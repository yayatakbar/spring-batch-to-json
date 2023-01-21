# Spring Batch Input JSON Output Write to DB using JPA
This repository contains a project which implements Spring Batch.

## Dependencies
This project used 3 dependencies :
* Lombok
* JPA
* Spring Batch

## Database
The database used in this project is **H2 Embedded Database**

## Getting Started
This project consists of 5 major packages :
* Model
* Repository
* Service
* Batch
* Controller

## How to run
* Run the main application
* Access http://localhost:8080/api/person/dummy to see the **dummy json** which is going to be saved to DB
* Access http://localhost:8080/api/person/real to see the **real data** inside **database** (you'll see empty response)
* Access http://localhost:8080/api/person/batch to execute the spring batch (saving dummy json to DB)
* Access http://localhost:8080/api/person/real again to see that the dummy json is **inserted** to DB
