# Nombre de la api REST
RotomNet api-REST

## Funcionalidades
La api REST de RotomNet tiene como objetivo actuar como servicio interno de la aplicación web "RotomNet", desde donde puede ser llamada en dos casos:
* Para enviar la información de un equipo Pokemon pseudoaleatorio a una dirección de correo electrónico especificada.
* Para publicar en la red social Twitter la obtención de una nueva carta tras un combate.

## URLs utilizadas
* /mail: se realiza una petición de tipo POST en cuyo cuerpo se encuentra tanto el contenido del mensaje como el destinatario. Como consecuencia de esta petición, se envía un correo electrónico desde rotomnetgroup@gmail.com a la dirección especificada, con los datos del equipo listos para ser copiados y pegados en la plantilla de importación de equipos en "Pokemon Showdown".
* /{user}/card/{pokemon}/tweet: se realiza una petición de tipo POST en cuya dirección se encuentran el nombre del usuario y de la carta obtenida. Además, el cuerpo contiene información relacionada con la rareza de la carta (si es shiny o no), y la cantidad general poseída por el usuario. Como consecuencia de esta petición, se publica en la cuenta de Twitter https://twitter.com/RotomNetForum un tweet con una imagen del pokemon obtenido, así como un texto informativo.

## Documentación interactiva
http://localhost:8080/swagger-ui/index.html#/
