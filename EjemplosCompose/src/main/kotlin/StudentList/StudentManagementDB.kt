package StudentList

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentManagementDB(
    private val dBManagement: IStudentRepository
) : IStudentManagement {

    override fun loadStudents(
        onFailure: (String) -> Unit
    ): List<String> {

        val students = mutableListOf<String>()
        val result = dBManagement.getAllStudents()

        result.onSuccess {studentList ->
            students.clear()
            students.addAll(studentList)
        }.onFailure {
            exception -> onFailure(exception.message ?: "No se pudieron cargar los datos de los estudiantes.") }

        return students
    }

    override fun saveStudents(
        students: List<String>,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        CoroutineScope(Dispatchers.Default).launch {
            val result = dBManagement.updateStudents(students)
            result.onSuccess {
                onSuccess("Estudiantes actualizados con éxito.")
            }.onFailure { exception ->
                onFailure(exception.message ?: "Error desconocido al actualizar la base de datos")
            }
        }
    }

}