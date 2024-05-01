package StudentList

import IGestorFicheros
import java.io.File
/**
 * Clase que implementa la interfaz IGestorFicheros para gestionar archivos y directorios.
 * @property consola El gestor de consola utilizado para mostrar mensajes de error.
 */
class GestorFicheros(private val consola: IGestorConsola): IGestorFicheros {
    /**
     * Comprueba si el directorio especificado por la ruta existe.
     * @param ruta La ruta del directorio a comprobar.
     * @return true si el directorio existe, false en caso contrario.
     */
    override fun comprobarDirectorio(ruta: String): Boolean {
        try {
            if (File(ruta).isDirectory)
                return true
        } catch (e:SecurityException){
            consola.mostrar("Error al comprobar $ruta: ${e.message}", true)
        }
        return false
    }
    /**
     * Comprueba si el fichero especificado por la ruta existe.
     * @param ruta La ruta del fichero a comprobar.
     * @return true si el fichero existe, false en caso contrario.
     */
    override fun comprobarFichero(ruta: String): Boolean {
        try {
            if (File(ruta).isFile)
                return true
        } catch (e:SecurityException){
            consola.mostrar("Error al comprobar $ruta: ${e.message}", true)
        }
        return false
    }
    /**
     * Escribe el mensaje en el fichero especificado.
     * @param fichero El objeto File que representa el fichero donde se escribirá el mensaje.
     * @param mensaje El mensaje a escribir en el fichero.
     * @return true si se escribió correctamente en el fichero, false en caso contrario.
     */
    override fun escribirFichero(fichero: File, mensaje: String): Boolean {
        try {
            fichero.bufferedWriter().use { out ->
                out.write(mensaje)
            }
        } catch (e:Exception){
            consola.mostrar("Error al escribir $fichero: ${e.message}", true)
            return false
        }
        return true
    }
    /**
     * Lee el contenido del fichero especificado y lo devuelve como una lista de cadenas de texto.
     * Cada elemento de la lista representa una línea del fichero.
     * @param fichero El objeto File que representa el fichero a leer.
     * @return Una lista de cadenas de texto con el contenido del fichero, o null si hubo un error al leer el fichero.
     */
    override fun leerFichero(fichero: File): List<String>? {
        val lista : List<String>
        try {
            lista = fichero.readLines()
        }catch (e:Exception){
            consola.mostrar("Error al leer $fichero: ${e.message}", true)
            return null
        }
        return lista
    }
    /**
     * Crea un directorio en la ruta especificada.
     * @param ruta La ruta donde se creará el directorio.
     * @return true si se creó el directorio correctamente, false en caso contrario.
     */
    override fun crearDirectorio(ruta: String): Boolean {
        val directorio = File(ruta)
        try {
            if (!directorio.isDirectory) {
                if (!directorio.mkdirs()) {
                    return false
                }
            }
        } catch (e: Exception) {
            consola.mostrar("Error al crear el directorio $ruta: ${e.message}", true)
            return false
        }
        return true
    }
    /**
     * Crea un fichero en la ruta especificada y escribe el mensaje en él.
     * @param ruta La ruta donde se creará el fichero.
     * @param mensaje El mensaje a escribir en el fichero.
     * @param sobreescribir Indica si se debe sobrescribir el fichero si ya existe. Por defecto es true.
     * @return Un objeto File que representa el fichero creado, o null si no se pudo crear.
     */
    override fun crearFichero(ruta: String, mensaje: String, sobreescribir: Boolean): File? {
        val fichero = File(ruta)
        crearDirectorio(fichero.parent)
        try {
            if (sobreescribir){
                fichero.writeText(mensaje)
            }else{
                fichero.createNewFile()
                if (mensaje.isNotEmpty()){
                    fichero.appendText(mensaje)
                }
            }
        }catch (e:Exception){
            consola.mostrar("Error al crear el fichero $ruta: ${e.message}",true)
            return null
        }
        return fichero
    }
    /**
     * Obtiene una lista de ficheros en el directorio especificado, ordenados alfabéticamente por sus nombres.
     * @param rutaDirectorio La ruta del directorio del que se obtendrán los ficheros.
     * @return Una lista de objetos File representando los ficheros en el directorio, ordenados por nombre,
     * o null si no se pudo obtener la lista de ficheros.
     */
    override fun obtenerFicherosOrdenadosPorLista(rutaDirectorio: String): List<File>? {
        if (!comprobarDirectorio(rutaDirectorio)) {
            return null
        }

        val directorio = File(rutaDirectorio)
        return directorio.listFiles()?.sortedBy { it.name }
    }
}