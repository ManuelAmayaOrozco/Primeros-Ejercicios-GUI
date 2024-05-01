import java.io.File
/**
 * Interfaz que define operaciones para gestionar archivos y directorios.
 */
interface IGestorFicheros {
    /**
     * Comprueba si el directorio especificado por la ruta existe.
     * @param ruta La ruta del directorio a comprobar.
     * @return true si el directorio existe, false en caso contrario.
     */
    fun comprobarDirectorio(ruta: String):Boolean
    /**
     * Comprueba si el fichero especificado por la ruta existe.
     * @param ruta La ruta del fichero a comprobar.
     * @return true si el fichero existe, false en caso contrario.
     */
    fun comprobarFichero(ruta: String):Boolean
    /**
     * Escribe el mensaje en el fichero especificado.
     * @param fichero El objeto File que representa el fichero donde se escribirá el mensaje.
     * @param mensaje El mensaje a escribir en el fichero.
     * @return true si se escribió correctamente en el fichero, false en caso contrario.
     */
    fun escribirFichero(fichero:File, mensaje:String):Boolean
    /**
     * Lee el contenido del fichero especificado y lo devuelve como una lista de cadenas de texto.
     * Cada elemento de la lista representa una línea del fichero.
     * @param fichero El objeto File que representa el fichero a leer.
     * @return Una lista de cadenas de texto con el contenido del fichero, o null si hubo un error al leer el fichero.
     */
    fun leerFichero(fichero: File): List<String>?
    /**
     * Crea un directorio en la ruta especificada.
     * @param ruta La ruta donde se creará el directorio.
     * @return true si se creó el directorio correctamente, false en caso contrario.
     */
    fun crearDirectorio(ruta: String):Boolean
    /**
     * Crea un fichero en la ruta especificada y escribe el mensaje en él.
     * @param ruta La ruta donde se creará el fichero.
     * @param mensaje El mensaje a escribir en el fichero.
     * @param sobreescribir Indica si se debe sobrescribir el fichero si ya existe. Por defecto es true.
     * @return Un objeto File que representa el fichero creado, o null si no se pudo crear.
     */
    fun crearFichero(ruta: String, mensaje: String, sobreescribir:Boolean = true):File?
    /**
     * Obtiene una lista de ficheros en el directorio especificado, ordenados alfabéticamente por sus nombres.
     * @param rutaDirectorio La ruta del directorio del que se obtendrán los ficheros.
     * @return Una lista de objetos File representando los ficheros en el directorio, ordenados por nombre,
     * o null si no se pudo obtener la lista de ficheros.
     */
    fun obtenerFicherosOrdenadosPorLista(rutaDirectorio: String): List<File>?
}