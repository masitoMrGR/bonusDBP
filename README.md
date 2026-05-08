## Configuración de Base de Datos

El proyecto utiliza PostgreSQL.

Por lo que antes de ejecutar la aplicación, es necesario que se modifique el archivo:

```text
src/main/resources/application.properties
```

y configurar las credenciales locales de PostgreSQL según su entorno.

Por ejemplo, en mi caso, he usado mi postgres y mi password durante el desarrollo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/flyaway
spring.datasource.username=postgres
spring.datasource.password=1234
```
Por lo que debe cambiar:

* nombre de base de datos,
* usuario,
* contraseña,

según la configuración local de PostgreSQL de usted.

---

## Ejecución del Tester
Para que los 35 test den correcto; en especial, para que el test `REVIEW_EMAIL_NOTIFICATION` funcione correctamente, es importante configurar el **Working Directory** de la aplicación Spring Boot apuntando a la carpeta raíz del proyecto tester. Debido a que yo desarrolle el proyecto desde 0 con otros nombres. Por ende mis archivos y carpetas son distintas y no estan en la misma carpeta base del tester.
Paa ello, la aplicación genera los archivos de simulación de correo dentro de:

```text
target/flight_booking_email_<bookingId>.txt
```

y el tester espera encontrar dichos archivos dentro de su propio directorio `target/`. Por lo que es necesario realizar algunos ajustes extras en el INTELLIJ.

### Configuración en IntelliJ IDEA

1. Ir a **Run > Edit Configurations**
2. Seleccionar la aplicación Spring Boot
3. Activar la opción **Working directory**
4. Colocar como ruta la carpeta raíz del tester

Luego ejecutar normalmente el tester:

```bash
java -jar week07-tester.jar test -u http://localhost:8080 -s -n
```

Con esta configuración el proyecto pasa correctamente todos los tests (`35/35`).
