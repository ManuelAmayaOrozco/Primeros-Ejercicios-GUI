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
fun StudentListScreen(studentViewModel: IStudentViewModel) {

    LaunchedEffect(key1 = true) {
        studentViewModel.loadStudents()
    }

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
                        newstudent = studentViewModel.newStudent.value,
                        onStudentChanged = { studentViewModel.newStudentChange(it) }
                    )

                    NewStudentButton(
                        buttonEnabled = studentViewModel.buttonEnabled,
                        onAddedStudent = { studentViewModel.addStudent() }
                    )
                }
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        StudentCounter(
                            studentList = studentViewModel.students.toMutableList()
                        )

                        StudentScreen(
                            studentList = studentViewModel.students.toMutableList(),
                            onDelete = { }
                        )

                        ClearButton(
                            onClear = { studentViewModel.clearStudents() }
                        )

                        SaveButton(
                            onSave = {
                                studentViewModel.saveStudents()
                            }
                        )
                    }
                }
            }

        }

        //RadioButton para elegir entre file o DB

        if (studentViewModel.showInfoMessage.value) {
            Toast(studentViewModel.infoMessage.value) {
                studentViewModel.showInfoMessage(false) }
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
    onClear: () -> Unit
) {
    Button(
        onClick = {
            onClear()
        },
    ) {
        Text(text = "Clear all")
    }
}

@Composable
fun TypeButton(
    onFile:() -> Unit,
    onDB:() -> Unit
) {
    Column {
        Button(
            onClick = onFile
        ) {
            Text(text = "File")
        }
        Button(
            onClick = onDB
        ) {
            Text(text = "Database")
        }
    }
}

@Composable
fun StudentCounter(
    studentList: List<String>
) {
    Text("Students: ${studentList.size}")
}

@Composable
fun StudentScreen(
    studentList: MutableList<String>,
    onDelete: () -> Unit
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
                    onDelete()
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

@Composable
fun ChooseStudentVMWindow(
    onChangeVmType: (Boolean) -> Unit,
    onCloseWindow: () -> Unit
){
    val windowState = rememberWindowState(size = DpSize(600.dp, 600.dp))

    Window(
        onCloseRequest = onCloseWindow,
        title = "Ejercicios",
        state = windowState
    ) {
        TypeButton(
            onFile = { onChangeVmType(true) },
            onDB = { onChangeVmType(false) }
        )
    }
}


@Composable
fun StudentListWindow(
    studentViewModel: IStudentViewModel,
    onCloseWindow: () -> Unit
) {
    val windowState = rememberWindowState(size = DpSize(1200.dp, 1000.dp))

    Window(
        onCloseRequest = onCloseWindow,
        title = "Ejercicios",
        state = windowState
    ) {
        StudentListScreen(
            studentViewModel
        )
    }
}

fun main() = application {

    var vmType by remember { mutableStateOf(true) }
    var showStudentListWindow by remember { mutableStateOf(false) }

    MaterialTheme {

        if (showStudentListWindow) {

            val studentViewModel = if (vmType) {
                //File

                val ruta = "src\\main\\kotlin\\Students.txt"
                val studentFile = File(ruta)
                val gestorConsola = GestorConsola()
                val gestorFicheros = GestorFicheros(gestorConsola)

                val studentManagementFile = StudentManagementFile(gestorFicheros, studentFile)

                StudentViewModel(studentManagementFile)
            }
            else {
                //Db
                val studentRepository = StudentRepository()

                val studentManagementDB = StudentManagementDB(studentRepository)
                StudentViewModel(studentManagementDB)
            }

            StudentListWindow(
                studentViewModel = studentViewModel,
                onCloseWindow = { exitApplication() }
            )
        }
        else {
            ChooseStudentVMWindow(
                onChangeVmType = { vmType = it },
                onCloseWindow = { showStudentListWindow = true }
            )
        }

    }


}