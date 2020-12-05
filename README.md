# User Service Application

This application is built for coding challenge. The application has 2 services, one for retrieval of user details and other for updating user details.

### Tech Stack

This application uses the below technologies for build, run and testing.

| Name | Version | Type |
| --- | --- | --- |
| Maven | 3.6.3 (or higher) | User Installation required (if not available) |
| Java | 1.8 (or higher) | User Installation required (if not available) |
| SpringBoot (core and JPA) | 2.3.3-RELEASE | Managed by application |
| H2 |1.4.200 | Managed by application |
| Hibernate - Validator | 6.1.5.Final | Managed by application |
| RestAssured | 3.3.0 | Managed by application |

### High Level Application Design
![Application Sequence Diagram GET][seqDiagramGet]
![Application Sequence Diagram PUT][seqDiagramPut]
### Database Model

This application uses in-memory H2 database. 
* DB is normalised to tables, USERS and ADDRESS
* USERS table has all the USER related data along with with Foreign key reference (ADDRESSID) to ADDRESS Table
* ADDRESS table has all address details of the user. There is no update to this table, anytime address is changed by PUT call, a new row is created and the corresponding entry in USERS table is updated with latest id.

![Database Model][dbmodel]

### Build
Clone the repository from github to local machine by navigating (or creating) to a folder/directory, then run the below command from git (or by using plugins in IDE)
```sh
git clone https://github.com/yagnatopalle/interview.git
```
Once dowloaded, a new folder `interview` would be created with all the source code.

Navigate into to folder and if using command prompt or powershell or terminal run the below command (On Windows, holding shift and right-click will given an option to open command prompt or powershell directly from the folder).

```sh
maven clean install
```

If using any IDE, Just import the project into the IDE and build as a maven project with the same arguments as above.

**Ensure MAVEN and JAVA paths are set in classpath.**
### Run the app
Once Maven build is successful, two new folders would be created called `target` (or `bin` based on IDE settings) and `logs` . Based on which tool is being used, there are few ways to run the app.

If using Terminal or command prompt or powershell, navigate into `target` folder and paste the below command and press enter.

``` sh
java -jar interview-ing-1.0.jar
```

If using an IDE, just select the imported project  and run as Java application.

Both the options will trigger the app deployment and after few seconds (5-10 seconds) a message similar to show below will appear on the console (IDE or terminal or shell).
```sh
2020-12-05 14:47:32,894 [main] INFO  au.com.interview.ing.Application                   - Started Application in 3.416 seconds (JVM running for 4.692)
```
A log file `UserServiceApp.log` within `logs` folder would also be updating and can be used to monitor application logs.

### Accessing/invoking the services
The application will by default run on port `8080`. There are 2 services which can be used, and the application pre-loads the DB with bunch of [mock data](#Preloaded-data) which can also be used in the services.

Postman

[Postman Collection][pc] can also be used to invoke services, just open the file `UserService.postman_collection.json` and paste the raw data into postman or just import the fiel as is.

Swagger
A [Swagger file][swagger] is also placed in the repo which can also be used to check the specification of both the services. Open [Swagger editor][se] and either paste the raw content into it or import the file to see the spec.

GET
```sh
URL: http://localhost:8080/userdetails/881504505
Response Code: 200
Response structure
{
    "title": "MR",
    "firstname": "FN3",
    "lastname": "LN3",
    "gender": "MALE",
    "address": {
        "street": "5690 KENT ST",
        "city": "SYDNEY",
        "state": "NSW",
        "postcode": 2000
    },
    "empId": 881504505
}
```

PUT
```sh
URL: http://localhost:8080/userdetails/881504505
Response Code: 204
Response Structure: Put will return 204 with NO-CONTENT, do a GET call again for same Id to see udpated results.
Request structure
{
    "title": "MRS",
    "firstname": "FIRST NAME",
    "lastname": "LAST NAME",
    "gender": "FEMALE",
    "address": {
        "street": "1 Park St",
        "city": "Sydney",
        "state": "NSW",
        "postcode": 2000
    }
}
```
Database

As long as the application is running, H2 console has been enabled and users can see the DB in runtime.

```sh
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Password: password
Sample Queries
SELECT * FROM ADDRESS ORDER BY ID DESC;
SELECT * FROM USERS ORDER BY UPDATEDDATETIME DESC;
```

### Preloaded data
The data is loaded into H2 When the application loads, if additional Mock data is needed, just edit the `data-h2.sql` in `src/main/resources`. Users table would need reference to addressId (PK of Address table which is auto_incremented), or if no address is needed, just set it to `null`.

| Employee Id | Title | First Name | Last Name | Gender | Street | City | State | Postcode |
| --- | --- | --- | --- | --- | --- | --- | --- | ---|
| 1232854 | MR | test | tsetlast | MALE | 12345 holling rd | SYDNEY | NSW | 2000 |
| 1234556 | MR | FN1 | LN1 | MALE | 275 KENT ST | SYDNEY | NSW | 2000 |
| 588636421 | MR | FN2 | LN2 | MALE | 9280 KENT ST | SYDNEY | NSW | 2000 |
| 881504505 | MR | FN3 | LN3 | MALE | 5690 KENT ST | SYDNEY | NSW | 2000 |
| 502390812 | MR | FN4 | LN4 | FEMALE | 4861 KENT ST | SYDNEY | NSW | 2000 |
| 683474520 | MR | FN5 | LN5 | MALE | 3812 KENT ST | SYDNEY | NSW | 2000 |
| 655268186 | MR | FN6 | LN6 | MALE | 6228 KENT ST | SYDNEY | NSW | 2000 |
| 608591793 | MR | FN7 | LN7 | FEMALE | 660 KENT ST | SYDNEY | NSW | 2000 |
| 847792233 | MR | FN8 | LN8 | MALE | 2165 KENT ST | SYDNEY | NSW | 2000 |
| 203351020 | MR | FN9 | LN9 | MALE | 9741 KENT ST | SYDNEY | NSW | 2000 |
| 34168551 | MR | FN10 | LN10 | FEMALE | 9567 KENT ST | SYDNEY | NSW | 2000 |
| 536835439 | MR | FN11 | LN11 | MALE | 8237 KENT ST | SYDNEY | NSW | 2000 |
| 1051251700 | MR | FN12 | LN12 | FEMALE | 3430 KENT ST | SYDNEY | NSW | 2000 |
| 990155538 | MR | FN13 | LN13 | MALE | 9225 KENT ST | SYDNEY | NSW | 2000 |
| 341699250 | MR | FN14 | LN14 | FEMALE | 4550 KENT ST | SYDNEY | NSW | 2000 |

[seqDiagramGet]: <docs/GetSeqDiagram.PNG?raw=true>
[seqDiagramPut]: <docs/PutSeqDiagram.PNG?raw=true>
[DBModel]: <docs/UserServiceDataModel.PNG?raw=true>
[pc]: <UserService.postman_collection.json>
[swagger]: <swagger.yaml>
[se]: <https://editor.swagger.io>