# Cliente Rest Envios UCP --

Sistema que permite gestionar un cliente con servicios rest desplegando por docker y conexion a base de datos

Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas.

## Requisistos Instalacion
- Instalar docker
- Instalar base de datos MYSQL

## Instalación 
- Descargar el Docker de la siguiente pagina https://www.docker.com/

## Ejecutando las pruebas
- Se verifica la ejecución de los contenedores: docker ps
- Se realizan pruebas funcionales del proyecto por medio de postman, por medio de la url localhost:8080/login por medio del username y password

## Despliegue
- Verificar los archivos de configuracion docker-compose.yml(Configuracion del contenedor) y application.properties(Se configura archivo de propiedades de la aplicación para apuntar al contenedor de BD) 
- Ejecutar la siguiente linea de comando en  Docker    docker-compose up -d para desplegar proyecto y base de datos

## Autores
- Diego Salinas 
- Hector Ramirez
