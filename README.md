# Drone Delivery Service
This is a service via REST API that allows clients to communicate with the drones for delivering medications. 

## Services include:
- Registering a drone.
- Loading a drone with medication items.
- Checking loaded medication items for a given drone. 
- Checking available drones for loading.
- Check drone battery level for a given drone.
- Prevent the drone from being loaded with more weight that it can carry.
- Prevent the drone from being in LOADING state if the battery level is below 25%.
- Periodic task to check drones battery levels and create history/audit event log.

## application.properties
spring.datasource.url=jdbc:h2:mem:dronedb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=drones
spring.datasource.password=
spring.jpa.defer-datasource-initialization=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.validator.apply_to_ddl=false
spring.jpa.properties.hibernate.globally_quoted_identifiers=true
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

## Run
Clone the repository, create your Spring boot app run configuration and start the application. 
The application will startup on port of 8080.

## Postman collection for testing: 
https://api.postman.com/collections/991494-2874dbdf-a564-4bb5-8972-2eff9aa35424?access_key=PMAT-01GR455QJ3SFZ5NV2FJ321MHXT 

## Generate Base64 image:
https://www.base64-image.de