# RSA_AES_postgres

Api que consiste en realizar el encpriptado y firma de un archivo tomando su ruta como parámetro dentro de la url que invoca el servicio

## Consideraciones:
- Se optó como forma de implementación de la solución una API para utilizar las facilidades de los decoradores ofrecidos por el framework de Spring Boot para la persistencia con la base de datos implementada en postgres de manera local, además de poder mostrar la solución en un microservicio o en una solución en un componente que incluya detalles adicionales como un patrónn de disñeo o un estilo arquitectónico específico.
- Para ello se tomaron las librerías de security ofrecidas por Java tanto para generar las instancias del cifrado de tipo AES con PKCS5 de padding (por defecto de Java) como para la generación de llaves tanto para el cifrado como para la lalve pública y privada para poder implementar el cifrado RSA.
- Solo se guarda en una misma tabla con tipos de datos bytea (similares a los blob de oracle, esto debido a la gran cantidad de información que se debe almacenar en un solo campo de la base de datos) además de un id generado secuencialmente.

  ## Referencias:
  - Generating a Secure AES Key in Java, para la generación de la llave aleatoria para el algoritmo de cifrado AES : https://www.baeldung.com/java-secure-aes-key
  - Spring-data-jpa storing blob, para almacenar los resultados en la base de datos a partir del ORM de Srping boot: https://stackoverflow.com/questions/31469136/spring-data-jpa-storing-blob
  - The Java Secure Randiom class, clase para poder generar el par de llaves para el cifrado mediante RSA: https://www.baeldung.com/java-secure-random
  - RSA Signing and verifying un Java, para conocer el algoritmo de firma digital en Java: https://stackoverflow.com/questions/21179959/rsa-signing-and-verifying-in-java
