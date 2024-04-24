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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                .fillMaxHeight()
        ) {
            Text("Ejemplo 1")
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
            Row(
                modifier = Modifier
                    .height(150.dp)
                    .background(Color.White)
                    .wrapContentSize(Alignment.TopCenter)
                    .border(BorderStroke(5.dp,Color.Red))
            ) {
                Text(
                    text = "Ejemplo 3",
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
                    text = "Ejemplo 4",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
    }
}

@Composable
fun Ejercicio5() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center)
    {
        Box(
            modifier = Modifier
                .height(150.dp)
                .background(Color.Cyan)
                .align(Alignment.TopCenter)
                .wrapContentSize(Alignment.Center)
                .fillMaxWidth()
        ) {
            Text(
                text = "BOX 1",
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(150.dp))
        Box(
            modifier = Modifier
                .height(150.dp)
                .background(Color.Gray)
                .align(Alignment.TopCenter)
                .wrapContentSize(Alignment.Center)
                .fillMaxWidth()
        ) {
            Text(
                text = "BOX 2",
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(150.dp))
        Box(
            modifier = Modifier
                .height(150.dp)
                .background(Color.Green)
                .align(Alignment.TopCenter)
                .wrapContentSize(Alignment.Center)
                .fillMaxWidth()
        ) {
            Text(
                text = "BOX 3",
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Spacer(modifier = Modifier.height(150.dp))
        Box(
            modifier = Modifier
                .height(150.dp)
                .background(Color.Magenta)
                .align(Alignment.TopCenter)
                .wrapContentSize(Alignment.Center)
                .fillMaxWidth()
        ) {
            Text(
                text = "BOX 3",
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun Ejercicio6() {
    Column(modifier = Modifier.fillMaxSize())
    {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .background(Color.Cyan)
                .weight(1F)
                .fillMaxSize()
        ) {
            Text(
                text = "Ejercicio 2",
                style = TextStyle(
                    fontSize = 30.sp,
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .border(BorderStroke(10.dp,Color.Black))
                    .background(Color.Yellow)
                    .padding(10.dp)
            )
        }
        Row(

        ) {
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .background(Color.Blue)
                    .weight(1F)
                    .fillMaxSize()
            ) {
                Text(
                    text = "PMDM",
                    style = TextStyle(
                        fontSize = 30.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(Color.Green)
                    .weight(1F)
                    .fillMaxSize()
            ) {
                Text(
                    text = "DAM 2",
                    style = TextStyle(
                        fontSize = 30.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
        }
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier
                .background(Color.Magenta)
                .weight(1F)
                .fillMaxSize()
        ) {
            Text(
                text = "...Combinando Column y Box",
                style = TextStyle(
                    fontSize = 22.sp,
                    color = Color.Yellow,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

//Ejercicio 7 ya no tengo ni idea

fun main() = application {
    val windowState = rememberWindowState(size = DpSize(1200.dp, 800.dp))

    Window(
        onCloseRequest = ::exitApplication,
        title = "Ejercicios",
        state = windowState
    ) {
        Ejercicio6()
    }
}