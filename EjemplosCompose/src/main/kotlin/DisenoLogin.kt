import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

@Composable
@Preview
fun LoginScreen() {

    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val buttonEnabled = user.isNotBlank() && password.isNotBlank()
    var passVisible by remember { mutableStateOf(false) }
    MaterialTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically),
            modifier = Modifier.fillMaxSize()
        ) {

            Usuario(
                user = user,
                onUserChanged = { user = it }
            )

            /*Usuario(user) { user = it }
            * Usuario(user, { user = it })*/

            Password(
                password = password,
                onPasswordChanged = { password = it },
                passVisible = passVisible,
                onVisibleChanged = { passVisible = it }
            )

            LoginButton(
                buttonEnabled = buttonEnabled,
                setBlank = {
                    user = ""
                    password = ""
                }
            )

        }
    }
}

@Composable
fun Usuario(
    user: String,
    onUserChanged:(String) -> Unit
) {
    OutlinedTextField(
        value = user,
        onValueChange = onUserChanged,
        label = { Text("Usuario") }
    )
}

@Composable
fun Password(
    password: String,
    onPasswordChanged:(String) -> Unit,
    passVisible: Boolean,
    onVisibleChanged:(Boolean) -> Unit
) {
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChanged,
        label = { Text("Contraseña") },
        visualTransformation = if (passVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconToggleButton(
                checked = passVisible,
                onCheckedChange = onVisibleChanged
            ) {
                Icon(
                    imageVector = if (passVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun LoginButton(
    buttonEnabled: Boolean,
    setBlank: () -> Unit
) {
    Button(
        onClick = setBlank,
        enabled = buttonEnabled
    ) {
        Text(text = "Login")
    }
}

fun main() = application {
    val windowState = rememberWindowState(size = DpSize(1200.dp, 800.dp))

    Window(
        onCloseRequest = ::exitApplication,
        title = "Ejercicios",
        state = windowState
    ) {
        LoginScreen()
    }
}