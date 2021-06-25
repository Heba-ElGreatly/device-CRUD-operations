# device-CRUD-operations
This application is to apply CRUD operations for Iot devices

# Tools
- spring boot
- h2 database

# Description
- Admin users can retrieve all devices with "waiting for activation" status, update device status or remove device, but for normal users they can retrieve devices with "waiting for activation" status only.
- Assumed that Operator code is the first three numbers of sim card number (vodafone --> 010, Mobinil --> 012, etisalat --> 011)
 

# How to Run Java Application

- open cmd at code location
- build the code using --> mvn clean package
- run the app using --> java -jar {jar path}\vf-IoT-1.0-SNAPSHOT.jar