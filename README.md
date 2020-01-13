# Lipo Ejercicios

## Ejercicio 1:

Un servicio que consuma la siguiente api: http://api.coindesk.com/v1/bpi/currentprice.json
* Considere una concurrencia de 1M de consultas por minuto. Obtenga los datos de dicha apì, imaginando que la misma no soporta tal cantidad de requests, logrando una respuesta rápida al cliente y teniendo la info actualizada. 
* El backend debe ser realizado con Spring Boot, db a elección y docker / docker-compose
* El frontend debe ser realizado con Angular o React
Agregar un README.md con:
  - Pasos para el deploy 
  - Arquitectura mínima para que soporte el tráfico de usuarios mencionado

#### Arquitectura seleccionada:

La arquitectura planteada en este ejercicio constaría de un cliente (fron-end que no se realizó por tiempo) que consumiría del backend en Java 8 el CurrentPriceController. 
Este es el encargado de comunicarse con Redis. Se seleccionó Redis para tranajar con la información en chache, que permite que la información esté actualizada y disponible para la cantidad de interacciones plantedas.
Se desarrolló un Cron que corre cada 1 minuto, donde se consume la informaciòn de la api del servicio y actualiza la información en Redis para ser consumida por el Cliente Web.
De esta forma se 

#### Deploy:
 1. Levantar Redis
> docker-compose up -d

2. Levantar la aplicación Java
> ./gradlew bootRun

Para ver la consola cliente de Redis, donde se guarda la info consultando la API: 
> localhost:8083

Al no llegar a hacer el Front-End, el servicio que consultaría el mismo sería: 
> localhost:8082/currentpriceCache


## Ejercicio 2:

Imagine que tiene un archivo de 1 peta, con info sobre viajes. Como obtendría la información del mismo para proveer de manera óptima a un dashboard, gráficos y estadísticas, en un tiempo razonable. 

#### Solución planteada:
Para este ejercicio utilizaría una arquiterctura basada en **Elasticsearch en AWS** dado que la información es estática y se puede routear fácilmente de esta manera. De esta forma será fácilmente escalable y seguro.
Este servicio nos da la posibilidad de obtener una gran cantidad de operaciones que permiten utilizar la información de una forma sencilla. Creando Cluster y Nodos donde se indexará la información, según lo definamos, y donde nos permitirá acceder de forma rápida y segura mediantes las APIs.
Se puede cargar la información en un S3 y se indexará para su uso mediantes las APIs y se podrá actualizar cuando lleguen nuevos documentos o nueva información.
Una vez indexada la información, se podrá acceder de forma rápida y sencilla mediante las REST API que se proponen.
Entonces configurado y subida la información deberíamos poder utilizar los Nodos definidos para obtener la información en formato JSON y mostrarla en un dashboard, obtener gráficos y estadísticas de forma rápida. También se puede hacer uso de herramientas como Kibana para explotar esa información y poder de forma rápida poder definir lo que se puede construir para luegos mostrar en alguna UI.
Si la información es estática, de igual forma que en el ejercicio anterior, se podrá actualziar la misma y utilizar cache para mejorar los tiempos de respuesta.



