import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    val icon = BitmapPainter(useResource("sample.png", ::loadImageBitmap))
    var showMainWindow by remember { mutableStateOf(true) }
    var showSecondWindow by remember { mutableStateOf(false) }

    if (showMainWindow) {
        MainWindow(onClose = { showMainWindow = false },
            icon = icon,
            buttonClick = {
                showMainWindow = false
                showSecondWindow = true
            }
        )
    }

    if (showSecondWindow) {
        SecondaryWindow(onClose = { showSecondWindow = false },
            icon = icon,
            buttonClick = {
                showMainWindow = true
                showSecondWindow = false
            })
    }

    if (!showMainWindow && !showSecondWindow) {
        exitApplication()
    }
}

@Composable
fun MainWindow(
    onClose: () -> Unit,
    icon: Painter?,
    buttonClick: () -> Unit
) {
    val mainWindowState = rememberWindowState()
    Window(
        onCloseRequest = onClose,
        title = "Ventana Principal",
        icon = icon,
        state = mainWindowState
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Este es el contenido de la ventana principal.")
            Button(onClick = buttonClick) {
                Text("Abrir Ventana Secundaria y cerrar esta")
            }
        }
    }
}

@Composable
fun SecondaryWindow(
    onClose: () -> Unit,
    icon: Painter?,
    buttonClick: () -> Unit
) {
    val secondaryWindowState = rememberWindowState()
    Window(
        onCloseRequest = onClose,
        title = "Ventana Secundaria",
        icon = icon,
        state = secondaryWindowState
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Este es el contenido de la ventana secundaria.")
            Button(onClick = buttonClick) {
                Text("Abrir Ventana Principal y cerrar esta")
            }
        }
    }
}