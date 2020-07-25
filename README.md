# URLShortener

Simple Spring boot API for short URL generation and retrieval

## Getting Started

### Prerequisites

```
SpringBoot
Maven
Java
Redis
Docker
Postman
```

### Installing

A step by step series of examples that tell you how to get a development env running

#### Running outside of Docker Containers

Start Redis-Server

```
Sudo redis-server
```
If the redis server ends due to the port is already in use. Kill it and try running it again

```
sudo service redis-server stop
```

Start Maven install

```
mvn clean install
```

Run the App with Spring boot
```
mvn spring-boot:run
```

### Local Tests

Using postman and web browser

1. Within Postman
Post URL 
```
http://localhost:8080/rest/url
```
2. Body of Post request - Can be whatever valid link you want
```
https://en.wikipedia.org/wiki/MurmurHash
```
This should return a id for you shorturl
e.g. 4d4f2dca

3. Within your web browser
Get Request 
```
http://localhost:8080/rest/url/4d4f2dca
```

Redirects to Murmur page


## Docker setup and execution

Run Maven Install
```
mvn clean install
```
Build it with Docker
```
sudo docker-compose build
```
Run it up with Docker
```
sudo docker-compose up
```

Done! and you can test it the same way as seen above

## Automated Tests

On the way

## Authors

* **Niall Ryan** 


