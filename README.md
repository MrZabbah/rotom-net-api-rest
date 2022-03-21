# Nombre de la api REST
RotomNet api-REST

## Funcionalidades
La api REST de RotomNet tiene como objetivo actuar como servicio interno de la aplicación web "RotomNet", desde donde puede ser llamada en dos casos:
* Para enviar la información de un equipo Pokemon pseudoaleatorio a una dirección especificada.
* Para publicar en redes sociales la obtención de una carta tras un combate.

## URLs utilizadas
* /mail: se realiza una petición de tipo POST en cuyo cuerpo se encuentra tanto el contenido del mensaje como el destinatario. Como consecuencia de esta petición, se envía un correo electrónico desde rotomnetgroup@gmail.com a la dirección especificada, con los datos del equipo listos para ser copiados y pegados en la plantilla de importación de equipos en "Pokemon Showdown".
