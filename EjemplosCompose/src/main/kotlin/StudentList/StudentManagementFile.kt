package StudentList

import IGestorFicheros
import java.io.File

class StudentManagementFile(
    private val fileManagement: IGestorFicheros,
    private val studentsFile: File
) : IStudentManagement {

    override fun loadStudents(
        onFailure: (String) -> Unit
    ) : List<String> {
        val loadedStudents = fileManagement.leerFichero(studentsFile)
        val students = mutableListOf<String>()
        if (loadedStudents != null) {
            students.addAll(loadedStudents)
        } else {
            onFailure("No se pudieron cargar los datos de los estudiantes.")
        }

        return students
    }

    override fun saveStudents(
        students : List<String>,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        var error = false
        val newStudentsFile = fileManagement.crearFichero(studentsFile.absolutePath, "")
        if (newStudentsFile != null) {
            for (student in students) {
                error = !fileManagement.escribirFichero(studentsFile, "$student\n")
                if (error) {
                    break
                }
            }
            if (error) {
                onFailure("Error")
            } else {
                onSuccess("Fichero guardado correctamente")
            }
        } else {
            onFailure("No se pudo generar el fichero studentList.txt")
        }
    }
}