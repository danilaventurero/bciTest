# bciTest
test BCI

API test bci es una api que nos permite crear, actualizar, leer y borrar usuarios de una base de datos en memoria.

INSTALACION

Usar los siguientes comandos para iniciar la api

1._ mvn clean install
2._ java -jar target/

La url host en la que inicia el proyecto es en localhost segun su configuracion:
podras usar el siguiente path:

http://localhost:8080

ENDPOINTS API BCI TEST

Para poder crear un usuario en la API puede importar el siguiente curl en su postman:

curl --location 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Juan Rodriguez",
"email": "jua3@rodriguez.org",
"password": "hunter2",
"phones": [
{
"number": "1234567",
"cityCode": "1",
"countryCode": "57"
},
{
"number": "12345679",
"cityCode": "2",
"countryCode": "58"
}
]
}'

con esto lograra crear un usuario con los datos enviados como bodyRequest en el endpoint.

Para modificar o actualizar la informacion de un usuario a continuacion dejo el curl del patch que sirve para actualizar el usuario:

curl --location --request PATCH 'http://localhost:8080/users/1' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Juan salazar",
"email": "juan@rodriguez.org",
"password": "hunter1",
"phones": [
{
"number": "12345671",
"cityCode": "11",
"countryCode": "571"
},
{
"number": "123456791",
"cityCode": "21",
"countryCode": "581"
}
]
}'

En caso de requerir consultar la informacion asociada a un usuario es necesario enviar el id del mismo como path param en la url de la API, a continuacion les dejo el curl para que puedan importarlo:

curl --location 'http://localhost:8080/users/1'

Y por ultimo en caso de querer borrar un usuario del sistema tambien debe proveer el id del usuario que desea remover como path param, a continuacion el curl para que puedan importarlo:

curl --location --request PUT 'http://localhost:8080/users/1'

