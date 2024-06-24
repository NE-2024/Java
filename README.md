## SPRING STUDENT-COURSE-MIS TEMPLATE

This is a template for a Spring Boot project. It includes the following features:

- Spring Boot
- Spring Security 
- Spring Data JPA
 - OpenAPI 3.0

## Getting Started
 - Clone the project :
```bash
git clone <url>
```

 - Edit the application.properties file :
```
  cd src/main/resources
    nano application.properties
```
- Edit the following properties :
```
spring.datasource.url=<database_url>
spring.datasource.username=<database_username>
spring.datasource.password=<database_password>

spring.mail.username=<your email username>
spring.mail.password<your email password>
adminKey = <admin_registration_key>

app_name=<The name of your application>
client.host=<your backend host>
front.host=<your frontend host>
```
- Access the project :
```bash
http://localhost:<desired port>/swagger-ui.html
```
N.B : You can only consume the apis when authenticated
## License
Apache License 2.0
