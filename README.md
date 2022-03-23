# RotomNet api-REST
La api-REST de RotomNet tiene como objetivo actuar como servicio interno de la aplicación web "RotomNet".

## Funcionalidades
* Enviar la información de un equipo Pokemon pseudoaleatorio a una dirección de correo electrónico especificada.
* Publicar en la red social Twitter la obtención de una nueva carta tras un combate. (Deben proporcionarse claves de autenticación OAuth 1.0a de Twitter en el fichero TwitterService.java)

## URLs utilizadas
* [ _/mail_ ]: se realiza una petición de tipo POST en cuyo cuerpo se encuentra tanto el contenido del mensaje como el destinatario. Como consecuencia de esta petición, se envía un correo electrónico desde rotomnetgroup@gmail.com a la dirección especificada, con los datos del equipo listos para ser copiados y pegados en la plantilla de importación de equipos en "Pokemon Showdown".

* [ _/{user}/card/{pokemon}/tweet_ ]: se realiza una petición de tipo POST en cuya dirección se encuentran el nombre del usuario y de la carta obtenida. Además, el cuerpo contiene información relacionada con la rareza de la carta (si es shiny o no), y la cantidad general poseída por el usuario. Como consecuencia de esta petición, se publica en la cuenta de Twitter https://twitter.com/RotomNetForum un tweet con una imagen del pokemon obtenido, así como un texto informativo.

## Documentación interactiva
Solo se encuentra disponible cuando el servicio está activo:
http://localhost:8080/swagger-ui/index.html#/
