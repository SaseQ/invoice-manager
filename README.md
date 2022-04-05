# Invoice Manager
![version](https://img.shields.io/badge/version-1.0.0-green)
![demo](https://img.shields.io/badge/demo-online-success)

> Live demo [_here_](http://marczuk.it/invoice-manager).<br>
> Frontend repo [_here_](https://github.com/SaseQ/invoice-manager-frontend).

## Table of Contents
* [General Info](#general-information)
* [Technologies Used](#technologies-used)
* [Features](#features)
* [Setup](#setup)
* [Screenshots](#screenshots)
* [Contact](#contact)


## General Information
This application is used to manage invoices. Accelerates the tax calculation process thanks to the use of an external system.


## Technologies Used
- Java 11
- Spring Boot 2.6.4
- PostgreSQL 42.3.3
- Liquibase 4.9.1
- Hibernate
- Docker
- TestContainers 1.16.3
- Swagger 2.9.2
- Lombok
- Maven 4.0.0
- JUnit 5
- Mockito


## Features
List the ready features here:
- Managing invoices
- Auto calculating taxes by country
- Auto calculating net value and gross price


## Setup
#### Clone the repository
```
git clone https://github.com/SaseQ/invoice-manager
```

#### Build the project
```
cd invoice-manager
docker-compose build
```

The project includes a ``web`` service, running the Java code, and a ``db`` service, running a PostgreSQL database.
See the ``docker-compose.yml`` file for details.

#### Run the project
```
docker-compose up
```


## Screenshots
![first screenshot](https://i.imgur.com/953FsD4.png)
<br><br><br>
![second screenshot](https://i.imgur.com/P2ejZUO.png)


## Contact
Created by [@SaseQ](https://github.com/SaseQ) - feel free to contact me!
