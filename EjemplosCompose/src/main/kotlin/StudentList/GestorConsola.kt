package StudentList

import java.util.*
/**
 * Implementación de la interfaz [IGestorConsola] que permite interactuar con la consola.
 * Esta clase proporciona métodos para mostrar mensajes, obtener números enteros y cadenas de texto,
 * así como pausar la ejecución del programa.
 */
class GestorConsola: IGestorConsola {
    /**
     * Muestra un mensaje en la consola.
     * @param message El mensaje a mostrar.
     * @param saltoLinea Indica si se debe imprimir una nueva línea después del mensaje.
     */
    override fun mostrar(message: String, saltoLinea: Boolean) {
        print(message)
        if (saltoLinea) {
            println()
        }
    }
    /**
     * Solicita al usuario ingresar un número entero desde la consola.
     * @param message El mensaje que se muestra para solicitar el número.
     * @return El número entero ingresado por el usuario.
     */
    override fun obtenerInt(message: String): Int {
        mostrar(message, false)
        var verify = false
        var num: Int? = null
        while (!verify) {
            num = readln().toIntOrNull()
            if (num == null) {
                mostrar("Número no válido, vuelve a intentarlo: ", false)
            }
            else {
                verify = true
            }
        }
        return num!!
    }
    /**
     * Solicita al usuario ingresar una cadena de texto desde la consola.
     * @param message El mensaje que se muestra para solicitar la cadena de texto.
     * @return La cadena de texto ingresada por el usuario.
     */
    override fun obtenerString(message: String): String {
        mostrar(message, false)
        return readln()
    }
    /**
     * Pausa la ejecución del programa, esperando a que el usuario presione alguna tecla para continuar.
     */
    override fun pausa() {
        println("\nPulsa enter para continuar...")
        Scanner(System.`in`).nextLine()
    }
}