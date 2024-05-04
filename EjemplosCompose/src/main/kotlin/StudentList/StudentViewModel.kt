package StudentList

import IGestorFicheros
import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

class StudentViewModel(
    private val fileManagement: IGestorFicheros,
    private val studentsFile: File
) : IStudentViewModel {

    companion object {
        private const val MAXCHARACTERS = 10
        private const val MAXNUMSTUDENTSVISIBLE = 7
    }

    private var _newStudent = mutableStateOf("")
    override val newStudent: State<String> = _newStudent

    private val _students = mutableStateListOf<String>()
    override val students: MutableList<String> = _students

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
        val loadedStudents = fileManagement.leerFichero(studentsFile)
        if (loadedStudents != null) {
            _students.addAll(loadedStudents)
        } else {
            updateInfoMessage("No se pudieron cargar los datos de los estudiantes.")
        }
    }

    override fun saveStudents() {
        var error = false
        val newStudentsFile = fileManagement.crearFichero(studentsFile.absolutePath, "")
        if (newStudentsFile != null) {
            for (student in students) {
                error = fileManagement.escribirFichero(studentsFile, "$student\n")
                if (error) {
                    break
                }
            }
            if (error) {
                updateInfoMessage("Error")
            } else {
                updateInfoMessage("Fichero guardado correctamente")
            }
        } else {
            updateInfoMessage("No se pudo generar el fichero studentList.txt")
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