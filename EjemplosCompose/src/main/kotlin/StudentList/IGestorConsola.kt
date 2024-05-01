package StudentList

/**
 * Interfaz que define los métodos para interactuar con la consola.
 */
interface IGestorConsola {
    /**
     * Muestra un mensaje en la consola.
     * @param message El mensaje a mostrar.
     * @param saltoLinea Indica si se debe imprimir una nueva línea después del mensaje.
     */
    fun mostrar(message: String, saltoLinea: Boolean)
    /**
     * Solicita al usuario ingresar un número entero desde la consola.
     * @param message El mensaje que se muestra para solicitar el número.
     * @return El número entero ingresado por el usuario.
     */
    fun obtenerInt(message: String): Int
    /**
     * Solicita al usuario ingresar una cadena de texto desde la consola.
     * @param message El mensaje que se muestra para solicitar la cadena de texto.
     * @return La cadena de texto ingresada por el usuario.
     */
    fun obtenerString(message: String): String
    /**
     * Pausa la ejecución del programa, esperando a que el usuario presione alguna tecla para continuar.
     */
    fun pausa()
}