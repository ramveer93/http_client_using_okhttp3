# http_client_using_okhttp3
A spring-boot application which can execute an external API calls for GET, PUT, POST, DELETE and HEAD http methods. It uses 
[Okhttp](https://square.github.io/okhttp/) java library.

## Use case
If you want to call external rest APIs inside your Java project then you can use this project for your work, The project has exposed 
an endpoint which can be used to call any external rest api.

## Requirements
- Java 8 or later
- apache-maven-3.6.0 or later
- okhttp 3.2.0 or later

## Building project
Its an standard maven project , which you can open in [eclipse](https://www.eclipse.org/downloads/) or [intellij](https://www.jetbrains.com/idea/) IDE by importing pom.xml file. Once opened in IDE , just build the project using below maven command

```maven
mvn clean install
```
you can also run sonar in this project using below command

```maven
mvn jacoco:prepare-agent test jacoco:report
```
and then 

```maven
mvn sonar:sonar
```
but for that you should have sonar install and started in your system and settings.xml which is pointing to your local sonar instance.
You can ignore above two commands if you just want the okhttp client.

## Usage
Once maven build is successful, the application will run on default port of `8080`. You can change it by changing the entry inside resources/application.properties as below
```Java Properties
server.port=8081
```
Once it is done , you can start the application.
From here you can either go to swagger documentation or use postman to hit the request. Below is the url for swagger documentation

```bash
http://localhost:8080/swagger-ui.html
```
This is how the swagger request will looks like:



