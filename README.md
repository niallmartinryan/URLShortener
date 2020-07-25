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



## Authors

* **Niall Ryan** 


