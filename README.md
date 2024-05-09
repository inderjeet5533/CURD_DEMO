# CURD_DEMO
Personal Info with curd demo




# Curl requests of all APIs
-------------------------------------------------
## saveinfo
curl --location 'localhost:8080/saveinfo' \
--header 'Content-Type: application/json' \
--data-raw '{
"firstName": "Inderjeet",
"lastName": "Singh",
"email": "xyz@domain.com",
"contact": "+917036785982",
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
"contact": "+917036785982",
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
