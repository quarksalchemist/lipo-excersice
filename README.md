# Lipo Ejercicios

## Ejercicio 1:

Un servicio que consuma la siguiente api: http://api.coindesk.com/v1/bpi/currentprice.json
* Considere una concurrencia de 1M de consultas por minuto. Obtenga los datos de dicha apì, imaginando que la misma no soporta tal cantidad de requests, logrando una respuesta rápida al cliente y teniendo la info actualizada. 
* El backend debe ser realizado con Spring Boot, db a elección y docker / docker-compose
* El frontend debe ser realizado con Angular o React
Agregar un README.md con:
  - Pasos para el deploy 
  - Arquitectura mínima para que soporte el tráfico de usuarios mencionado

Deploy:
1. Levantar Redis
> docker-compose up -d

2. Levantar la aplicación Java
> ./gradlew bootRun

> Ver consola cliente de Redis, donde se guarda la info consultando la API: localhost:8083
> Al no llegar a hacer el Front-End, el servicio que consultaría el mismo sería: localhost:8082/currentpriceCache

Arquitectura seleccionada:
Se se

## Ejercicio 2:

Imagine que tiene un archivo de 1 peta, con info sobre viajes. Como obtendría la información del mismo para proveer de manera óptima a un dashboard, gráficos y estadísticas, en un tiempo razonable. 
