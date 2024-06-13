DESARROLLADO POR:
- NICOLAS RIOS
- MIGUEL ESTEBAN BRAVO
- OSCAR ARIAS
  

Busca Amigos en Neo4j - Aplicación Java
Descripción
Esta aplicación Java permite a los usuarios buscar y listar amigos almacenados en una base de datos Neo4j utilizando una interfaz gráfica de usuario (GUI) construida con Swing. La aplicación se conecta a la base de datos Neo4j mediante JDBC y ejecuta consultas Cypher para recuperar los datos de los amigos.

Características
Buscar Amigos: Permite buscar amigos cuyos nombres contienen una cadena específica.
Listar Todos los Amigos: Muestra una lista de todos los amigos en la base de datos.
Interfaz Gráfica de Usuario (GUI): Proporciona una interfaz amigable y fácil de usar para interactuar con la base de datos.
Requisitos
Java 8 o superior: Asegúrate de tener una versión compatible de Java instalada.
Neo4j 3.0 o superior: Necesitas una instancia de Neo4j en funcionamiento.
Driver JDBC para Neo4j: Asegúrate de tener el driver JDBC de Neo4j disponible en tu proyecto.

Configuración

Configurar la base de datos Neo4j:

Asegúrate de que Neo4j esté instalado y funcionando.
Crea nodos Amigo en Neo4j con la propiedad nombre.
Configurar el proyecto Java:

Asegúrate de que los archivos .java estén en el paquete correcto (en este caso, appneo4j).
Incluye el driver JDBC de Neo4j en tu proyecto. Puedes descargarlo del repositorio de Maven.

Ejecución
Ejecutar la Aplicación:

Ejecuta la clase principal BuscarFriendsJFrame.
La ventana de la aplicación se abrirá, mostrando la interfaz gráfica.
Buscar Amigos:

Ingresa un nombre o parte de un nombre en el campo de texto.
Haz clic en el botón "Buscar".
Los resultados aparecerán en el área de texto, mostrando las relaciones de amistad encontradas.
Listar Todos los Amigos:

Haz clic en el botón "Listar Todos".
Todos los nombres de los amigos en la base de datos se mostrarán en el área de texto.
