package StudentList

import IGestorFicheros
import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

class StudentViewModelDB(
    private val dBManagement: IStudentRepository,
) : IStudentViewModel {

    companion object {
        private const val MAXCHARACTERS = 10
        private const val MAXNUMSTUDENTSVISIBLE = 7
    }

    private var _newStudent = mutableStateOf("")
    override val newStudent: State<String> = _newStudent

    private val _students = mutableStateListOf<String>()
    override val students: List<String> = _students

    private val _infoMessage = mutableStateOf("")
    override val infoMessage: State<String> = _infoMessage

    private val _showInfoMessage = mutableStateOf(false)
    override val showInfoMessage: State<Boolean> = _showInfoMessage

    private val _selectedIndex = mutableStateOf(-1) // -1 significa que no hay selección
    override val selectedIndex: State<Int> = _selectedIndex

    override fun addStudent() {
        if (_newStudent.value.isNotBlank()) {
            _students.add(_newStudent.value.trim())
            _newStudent.value = ""
        }
    }

    override fun removeStudent(index: Int) {
        if (index in _students.indices) {
            _students.removeAt(index)
        }
    }

    override fun loadStudents() {
        val result = dBManagement.getAllStudents()
        result.onSuccess {studentList ->
            _students.clear()
            _students.addAll(studentList)
        }.onFailure { exception -> updateInfoMessage(exception.message ?: "No se pudieron cargar los datos de los estudiantes.") }
    }

    override fun saveStudents() {
        CoroutineScope(Dispatchers.Default).launch {
            val result = dBManagement.updateStudents(_students)
            result.onSuccess {
                updateInfoMessage("Estudiantes actualizados con éxito.")
            }.onFailure { exception ->
                updateInfoMessage(exception.message ?: "Error desconocido al actualizar la base de datos")
            }
        }
    }

    override fun clearStudents() {
        _students.clear()
    }

    override fun shouldShowScrollStudentListImage() = _students.size > MAXNUMSTUDENTSVISIBLE

    override fun newStudentChange(name: String) {
        if (name.length <= MAXCHARACTERS) {
            _newStudent.value = name
        }
    }

    override fun studentSelected(index: Int) {
        _selectedIndex.value = index
    }

    private fun updateInfoMessage(message: String) {
        _infoMessage.value = message
        _showInfoMessage.value = true
        CoroutineScope(Dispatchers.Default).launch {
            delay(2000)
            _showInfoMessage.value = false
            _infoMessage.value = ""
        }
    }

    override fun showInfoMessage(show: Boolean) {
        _showInfoMessage.value = show
    }

}