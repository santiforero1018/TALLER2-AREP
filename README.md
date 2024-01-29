# TALLER #1, APLICACIONES DISTRIBUIDAS

Para este proyecto, se construira una aplicación que cuente con un servidor fachada para realizar peticiones a un API externo de peliculas, dicho aplicativo tendra una estructura de datos que haga las funciones de una memoria cache
para almacenar temporalmente peticiones realizadas a la API externa. Para cumplir con lo solicitado, se hara uso de la API [omdbapi](https://www.omdbapi.com)

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
git clone https://github.com/santiforero1018/TALLER-1-AREP.git
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



![imagen](https://github.com/santiforero1018/TALLER-1-AREP/assets/88952698/0f7864b5-3299-4a94-93d4-78200f522c31)


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

con estos comandos, se vuelve a compilar el codigo y lo pone en ejecución, recuerde entrar en la carpeta .\taller1 para empezar a ejecutar

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

## Acknowledgments

* Especial Agradecimiento al profesor [Luis Daniel Benavides Navarro](https://ldbn.is.escuelaing.edu.co/) por brindar el conocimiento necesario en la realización de este trabajo

