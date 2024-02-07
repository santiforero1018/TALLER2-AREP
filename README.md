# TALLER #2, APLICACIONES DISTRIBUIDAS

Usando la implementación de un proyecto anterior, ahora lo que se procedera a realizar, es leer archivos del disco duro de un servidor (en este caso, serian nuestras computadoras) y dichos archivos seran un html, css y javascript 

## PARA EMPEZAR

Se debe contar con un Sistema Operativo capaz de correr un IDE para poder realizar la ejecución el aplicativo, pues este cuenta con los siguientes componentes:
* [Java version 17](https://www.oracle.com/co/java/technologies/downloads/) - Lenguaje de programación usado.
* [Maven](https://maven.apache.org/download.cgi) - Gestor de dependencias del proyecto
* [Git](https://git-scm.com/downloads) - Gestion de versiones del proyecto 

### PREREQUISITOS

1. Contar con IDE para la ejecución del proyecto
2. Contar con las herramientas mencionadas anteriormente
3. Al tenerlas, ejecutar el siguiente comando en la maquina

```bash
git clone https://github.com/santiforero1018/TALLER2-AREP.git
```

### JAVADOC
Usando el siguiente comando: 
```
mvn site
```
se genera la documentación del proyecto, aunque ya se encuentra generada. Para acceder a ella, se encuentra en la carpeta targe/site en el archivo index.html


### DESCRIPCION DEL PROYECTO

Un proyecto construido por 4 clases java, la clase Main es la que pondra en funcionamiento todo el aplicativo, la clase Webserver pondra en ejecución el servidor web que retornara la pagina correspondiente para
realizar las consultas. Por medio de la clase APIRestFacade se realizara la petición correspondiente a la API externa y una clase Cache para almacenar las peticiones realizadas y realizar un eficiente uso de recursos.

Para la parte del front, se añadieron archivos html, css y js para ejecutar y mostrar en el browser cliente, al entrar, se vera inicialmente de la siguiente manera, más adelante se muestra como se veria despues de realizar una petición.

![imagen](https://github.com/santiforero1018/TALLER2-AREP/assets/88952698/fffc97a1-4f69-416a-a310-817fa74d559b)


## INICIANDO EL PROYECTO

```
1. En un IDE de desarrollo, se ejecuta la clase Main.java, lo mas recomendable es usar Visual Studio Code y las extensiones para java y ejecutarla
2. En un browser, ingresar a la URL http://localhost:35000
3. En la barra de busqueda, insertar el nombre de la pelicula a buscar
```

Si desea hacerlo usando la linea de comandos, use los siguientes:
```
mvn clean compile
mvn exec:java
```

con estos comandos, se vuelve a compilar el codigo y lo pone en ejecución, recuerde entrar en la carpeta .\taller2 para empezar a ejecutar

### Añadidos del proyecto
anteriormente, se mandaba todo el codigo de una pagina html dentro de una etructura basica de java para poderla mostrar al cliente, ahora, el servidor de java lee los archivos del disco del servidor en curso, y este retorna 
todo lo relacionado dentro de la pagina html.

Cuando ingrese a la pagina, ahora se vera una pagina con algunos estilos estandares configurados con hojas de estilo css, que tambien se encuentran dentro de la estructura del proyecto.


![imagen](https://github.com/santiforero1018/TALLER2-AREP/assets/88952698/f21689ef-14e5-4cdd-bed1-ac4255d748ef)



## DESARROLLADO CON

* [Java version 17](https://www.oracle.com/co/java/technologies/downloads/) - Lenguaje de programación usado.
* [Maven](https://maven.apache.org/download.cgi) - Gestor de dependencias del proyecto
* [Git](https://git-scm.com/downloads) - Gestion de versiones del proyecto
* [omdbapi](https://www.omdbapi.com) - API externa para realizar consultas

<!--
## Version

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). -->

## Autor

* **Santiago Forero Yate** - [santiforero1018](https://github.com/santiforero1018)

<!--
## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details -->

## Agradecimientos

* Especial Agradecimiento al profesor [Luis Daniel Benavides Navarro](https://ldbn.is.escuelaing.edu.co/) por brindar el conocimiento necesario en la realización de este trabajo

