# Accenture
- Prueba para la consultora Accenture.

Se realizo las pruebas que se pidieron en el correo.

* El sistema consta de 2 controladores los cuales 1 es publico y el otro es privado, por lo cual los metodos que se encuentran dentro del controlador publico
pueden ser consumidos sin necesidad de contener el token dentro de la cabecera http, para el contenedor pricado si es necesario que contenga el token
dentro de la cabecera y debe ser valido para poder consumir el metodo invocado http.

* Se implemento la seguridad por token JWT por lo cual el sistema consta de varios Filter los cuales son invocados en cada llamada http que se haga al
sistema, el token tiene duracción de 15 minutos, por lo cual pasado ese tiempo el token es invalido.

* El sistema integra SpringSecurity

* La estructura del proyecto es de SpringBoot con java 1.8 y Tomcat Embebbido

* Se usa BD en memoria H2

* La contraseña se encripta con Criptografía no reversible usando el algoritmo (BCryptPasswordEncoder)


