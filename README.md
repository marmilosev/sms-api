# sms-api

Applications for my master's degree on the topic 'Comparison of Java and Kotlin microservice architecture based on the
Spring boot framework'

- technologies used: Java, Kotlin, Spring Boot, Docker and JWT (Jason Web Token)

## HTML-API-Docs
- HTML and CSS page for API documentation
- created by example of https://github.com/floriannicolas/API-Documentation-HTML-Template

## sms-api-java
- java application with REST API and sending messages via Infobip SMS API

## sms-api-kotlin
- kotlin application with REST API and sending messages via Infobip SMS API

### Structure:
 apigateway - service for communicating with and without Jason Web Token (JWT)

 authservice - service for registering user, generating and validation of JWT token

 discoveryservice - service to discover all other services with annotation @EnableDiscoveryClient

 sendmessageservice - REST API for messages, using HTTP methods GET (all and by id), POST, PUT and DELETE and also sending message with POST request and Infobip SMS API

 userservice - REST API for users, using HTTP methods GET (all and by id), POST, PUT and DELETE


 ### Other info:
 - using POSTGRESQL database 
 - to use it with Docker:
 ```
 sudo docker run --name [NAME] -e POSTGRES_PASSWORD [PASSWORD] -d - p 5432:5432 [NAME_OF_IMAGE]
 sudo docker start [NAME]
 ```
 - in application.properties of a project use:
 ```
 spring.jpa.hibernate.ddl-auto=update
spring.sql.init.platform=postgres
spring.datasource.url=jdbc:postgresql://localhost:5432/YOUR_DATABASE_NAME
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.show-sql= true
 ```

 DEPENDENCIES:
 - Creating project with Spring Initializr
 - spring-boot-starter-data-jpa
 ```
 <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
			<version>3.1.1</version>
		</dependency>
 ```
 - spring-boot-starter-web
 ```
 <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>3.1.1</version>
</dependency>
 ```
 - lombok
 ```
 <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.28</version>
    <scope>provided</scope>
</dependency>
 ```
 - spring-boot-starter-test
 ```
 <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <version>3.1.1</version>
    <scope>test</scope>
</dependency>

 ```
 - argon2-jvm
 ```
 <dependency>
			<groupId>de.mkammerer</groupId>
			<artifactId>argon2-jvm</artifactId>
			<version>2.5</version>
		</dependency>
 ```
 - spring-boot-starter-validation
 ```
 <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
			<version>3.1.1</version>
		</dependency>
 ```
 - modelmapper
 ```
 <!-- https://mvnrepository.com/artifact/org.modelmapper/modelmapper -->
		<dependency>
			<groupId>org.modelmapper</groupId>
			<artifactId>modelmapper</artifactId>
			<version>3.1.1</version>
		</dependency>
 ```
 - postgresql
 ```
 <!-- https://mvnrepository.com/artifact/org.postgresql/postgresql -->
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<version>42.6.0</version>
		</dependency>
 ```
 - spring-cloud-starter-netflix-eureka-client
 ```
 <!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-netflix-eureka-client -->
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
			<version>4.0.2</version>
		</dependency>
 ```
 - spring-boot-starter-webflux
 ```
 <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-webflux -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-webflux</artifactId>
			<version>3.0.6</version>
		</dependency>
 ```
 - infobip-api-java-clien
 ```
 <dependency>
			<groupId>com.infobip</groupId>
			<artifactId>infobip-api-java-client</artifactId>
			<version>4.0.0</version>
		</dependency>
 ```
 - spring-cloud-starter-netflix-eureka-server
 ```
 <!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-netflix-eureka-server -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    <version>4.0.2</version>
</dependency>
 ```
 - java-jwt
 ```
 <dependency>
			<groupId>com.auth0</groupId>
			<artifactId>java-jwt</artifactId>
			<version>4.4.0</version>
		</dependency>
 ```
 - spring-security-config
 ```
 <!-- https://mvnrepository.com/artifact/org.springframework.security/spring-security-config -->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-config</artifactId>
    <version>6.1.1</version>
</dependency>
 ```
 - spring-security-web
 ```
 <!-- https://mvnrepository.com/artifact/org.springframework.security/spring-security-web -->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-web</artifactId>
    <version>6.1.1</version>
</dependency>
 ```
 - spring-security-core
 ```
 <!-- https://mvnrepository.com/artifact/org.springframework.security/spring-security-core -->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-core</artifactId>
    <version>6.1.1</version>
</dependency>
 ```
 - spring-cloud-starter-gateway
 ```
 <!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-gateway -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
    <version>4.0.6</version>
</dependency>
 ```
 - spring-boot-configuration-processor
 ```
 <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-configuration-processor -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <version>3.1.1</version>
</dependency>
 ```
 - in apigateway for spring-cloud-starter-netflix-eureka-client
 ```
 <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>com.sun.jersey</groupId>
                    <artifactId>jersey-client</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>com.sun.jersey</groupId>
                    <artifactId>jersey-core</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>com.sun.jersey.contribs</groupId>
                    <artifactId>jersey-apache-client4</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
 ```