import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

@Composable
fun Ejercicio1() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(Color.Cyan)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun Ejercicio2() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        Box(
            modifier = Modifier
                .height(150.dp)
                .background(Color.Cyan)
                .align(Alignment.Center)
                .wrapContentSize(Alignment.BottomCenter)
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
        ) {
            Text(
                text = "Esto es un EJEMPLO del uso de Box",
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun Ejercicio3() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        Column(
            modifier = Modifier
                .height(150.dp)
                .background(Color.Red)
                .align(Alignment.TopCenter)
                .wrapContentSize(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Ejemplo 1",
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
            Column(
                modifier = Modifier
                    .height(150.dp)
                    .background(Color.Gray)
                    .wrapContentSize(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Ejemplo 2",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
                Column(
                    modifier = Modifier
                        .height(150.dp)
                        .background(Color.Cyan)
                        .wrapContentSize(Alignment.TopCenter),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Ejemplo 3",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Column(
                        modifier = Modifier
                            .height(150.dp)
                            .background(Color.Green)
                            .wrapContentSize(Alignment.TopCenter),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Ejemplo 4",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Ejercicio4() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier
                    .height(150.dp)
                    .background(Color.White)
                    .wrapContentSize(Alignment.TopCenter)
                    .border(BorderStroke(5.dp,Color.Red))
            ) {
                Text(
                    text = "Ejemplo 1",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Row(
                modifier = Modifier
                    .height(150.dp)
                    .background(Color.White)
                    .wrapContentSize(Alignment.TopCenter)
                    .border(BorderStroke(5.dp,Color.Blue))
            ) {
                Text(
                    text = "Ejemplo 2",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

fun main() = application {
    val windowState = rememberWindowState(size = DpSize(1200.dp, 800.dp))

    Window(
        onCloseRequest = ::exitApplication,
        title = "Ejercicios",
        state = windowState
    ) {
        Ejercicio4()
    }
}