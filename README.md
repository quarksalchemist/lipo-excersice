# lipo
Ejercicio 1:


Arquitectura seleccionada:

Deploy:
1. Levantar Redis
> docker-compose up -d

2. Levantar la aplicación Java
> ./gradlew bootRun

> Ver consola cliente de Redis, donde se guarda la info consultando la API: localhost:8083
> Al no llegar a hacer el Front-End, el servicio que consultaría el mismo sería: localhost:8082/currentpriceCache
