# CURD_DEMO
Personal Info with curd demo
### Java version --> 17
### Springboot version 3.2.5

## Curd Operation implemented
## Database H2
## Junit Test Cases written.

# To build project maven should be installed in your system.
    run --> mvn clean install

# To build docker image Docker should be installed in your system.
    docker build -t inderjeet/curd_demo .

# To Run docker image
    docker run -p 8080:8080 inderjeet/curd_demo

# Curl requests of all APIs
-------------------------------------------------
## saveinfo
curl --location 'localhost:8080/saveinfo' \
--header 'Content-Type: application/json' \
--data-raw '{
"firstName": "Inderjeet",
"lastName": "Singh",
"email": "xyz@domain.com",
"contact": "+911111111111",
"address": {
"addressLine1": "sad sd sadasd",
"addressLine2": "asdas asdasd asd",
"city":"teracina",
"state":"Latina",
"country":"Italy",
"pinCode":"49102"
}
}'

--------------------------------------------------
--------------------------------------------------
## getinfo
curl --location 'localhost:8080/getinfo'

--------------------------------------------------
--------------------------------------------------
## updateinfo
curl --location --request PUT 'localhost:8080/updateinfo/2' \
--header 'Content-Type: application/json' \
--data-raw '{
"infoId": 2,
"firstName": "XYZ",
"lastName": "Test",
"email": "xyz@domain.com",
"contact": "+911111111111",
"address": {
"addressLine1": "sad sd sadasd",
"addressLine2": "asdas asdasd asd",
"city":"latina",
"state":"Latina",
"country":"Italy",
"pinCode":"49102"
}
}'

--------------------------------------------------
--------------------------------------------------
## deleteinfo
curl --location --request DELETE 'localhost:8080/deleteinfo/2'

--------------------------------------------------

# React frontend to use those APIs --> https://github.com/inderjeet5533/curd-demo-reactjs
