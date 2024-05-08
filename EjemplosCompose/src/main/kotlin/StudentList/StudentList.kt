package StudentList

import java.io.File
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.*
import kotlinx.coroutines.delay

@Composable
fun MainScreen(studentList: MutableList<String>,
               studentFile: File, gestorFicheros: GestorFicheros,
               studentViewModelFile: StudentViewModelFile,
               studentViewModelDB: StudentViewModelDB) {

    val newstudent by studentViewModelFile.newStudent
    val buttonEnabled = newstudent.isNotBlank()
    var result = false
    var toastSummon by remember { mutableStateOf(false) }
    var toastMessage = ""
    var typeFile by remember { mutableStateOf(true) }

    MaterialTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically),
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxSize().weight(3f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(end = 20.dp)
                ) {
                    NewStudent(
                        newstudent = newstudent,
                        onStudentChanged = { studentViewModelFile.newStudentChange(it) }
                    )

                    NewStudentButton(
                        buttonEnabled = buttonEnabled,
                        onAddedStudent = { studentViewModelFile.addStudent() }
                    )
                }
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        StudentCounter(
                            studentList = studentList
                        )

                        StudentScreen(
                            studentList = studentList
                        )

                        ClearButton(
                            studentList = studentList
                        )

                        TypeButton()

                        SaveButton(
                            onSave = {
                                var message = ""
                                for (student in studentList) {
                                    message += "$student\n"
                                }
                                result = gestorFicheros.escribirFichero(studentFile, message)
                                toastSummon = true
                                toastMessage = if (result) {
                                    "Students saved successfully."
                                } else {
                                    "Couldn't properly save students to file."
                                }
                            }
                        )
                    }
                }
            }

        }

        //RadioButton para elegir entre file o DB

        if (toastSummon) {
            Toast(toastMessage) {
                toastSummon = false
                toastMessage = ""}
        }

        LaunchedEffect(toastSummon){
            if (toastSummon) {
                delay(2000)
                toastMessage = ""
                toastSummon = false
            }
        }
    }
}

@Composable
fun NewStudent(
    newstudent: String,
    onStudentChanged:(String) -> Unit
) {
    OutlinedTextField(
        value = newstudent,
        onValueChange = onStudentChanged,
        label = { Text("New Student Name") }
    )
}

@Composable
fun NewStudentButton(
    buttonEnabled: Boolean,
    onAddedStudent:() -> Unit
) {
    Button(
        onClick = onAddedStudent,
        enabled = buttonEnabled,
    ) {
        Text(text = "Add new student")
    }
}

@Composable
fun ClearButton(
    studentList: MutableList<String>
) {
    Button(
        onClick = {
            studentList.clear()
        },
    ) {
        Text(text = "Clear all")
    }
}

@Composable
fun TypeButton() {

}

@Composable
fun StudentCounter(
    studentList: MutableList<String>
) {
    Text("Students: ${studentList.size}")
}

@Composable
fun StudentScreen(
    studentList: MutableList<String>
) {
    Column(
        modifier = Modifier
            .border(BorderStroke(width = 3.dp, color = Color(182, 108, 224)))
            .padding(5.dp)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight(0.78f)
                .width(240.dp)
                .padding(10.dp)
        ) {
            items(studentList){
                    item -> ListStudentRow(
                item = item,
                onDelete = {
                    studentList.remove(item)
                }
                    )
            }
        }
    }
}

@Composable
fun ListStudentRow(
    item: String,
    onDelete: () -> Unit) {
    Box(modifier = Modifier
        .size(height = 75.dp, width = 300.dp)) {
        Text(
            text = item,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 100.dp))
        SingleStudentDeleter(onDelete)
    }
}

@Composable
fun SingleStudentDeleter(
    onDelete: () -> Unit
) {
    Button(
        onClick = onDelete
    ) {
        Text("Delete")
    }
}

@Composable
fun SaveButton(
    onSave:() -> Unit
) {
    Button(
        onClick = onSave
    ) {
        Text("Save changes")
    }
}

@Composable
fun Toast(
    message: String,
    toastCloser: () -> Unit) {
    Dialog(
        onCloseRequest = toastCloser,
        state = rememberDialogState(position = WindowPosition(Alignment.Center))
    ) {
        Text(message)
    }
}

fun main() = application {
    val windowState = rememberWindowState(size = DpSize(1200.dp, 800.dp))
    val gestorConsola = GestorConsola()
    val gestorFicheros = GestorFicheros(gestorConsola)
    val studentRepository = StudentRepository()
    val ruta = "src\\main\\kotlin\\Students.txt"
    val studentFile = File(ruta)
    val studentViewModelFile = StudentViewModelFile(gestorFicheros, studentFile)
    val studentViewModelDB = StudentViewModelDB(studentRepository)

    val studentList = studentViewModelFile.students
    val studentFileList = gestorFicheros.leerFichero(studentFile)

    if (studentFileList != null) {
        for (student in studentFileList) {
            studentList.add(student)
        }
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Ejercicios",
        state = windowState
    ) {
        MainScreen(studentList, studentFile, gestorFicheros, studentViewModelFile, studentViewModelDB)
    }
}