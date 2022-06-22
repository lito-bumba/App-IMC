package com.litobumba.appimc.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.litobumba.appimc.AppViewModel
import com.litobumba.appimc.Resultado
import com.litobumba.appimc.ui.theme.CorPrimaria

@Composable
fun TelaPrincipal(viewModel: AppViewModel, expand: () -> Unit) {

    var altura by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
            .verticalScroll(rememberScrollState())
    ) {

        val focus = LocalFocusManager.current

        Text(
            text = "Calculadora IMC",
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            fontStyle = FontStyle.Italic,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(20.dp))
        Spacer(modifier = Modifier.height(100.dp))

        OutlinedTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(
                onDone = { focus.moveFocus(FocusDirection.Down) }
            ),
            value = altura,
            onValueChange = { altura = it },
            singleLine = true,
            label = { Text("Altura", fontSize = 18.sp) },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 20.sp
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                cursorColor = Color.Black,
                focusedLabelColor = CorPrimaria,
                unfocusedLabelColor = CorPrimaria,
                backgroundColor = Color.Transparent,
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth(.9F)
        )
        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(
                onDone = { focus.moveFocus(FocusDirection.Up) }
            ),
            value = peso,
            onValueChange = { peso = it },
            singleLine = true,
            label = { Text("Peso", fontSize = 18.sp) },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 20.sp
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                cursorColor = Color.Black,
                focusedLabelColor = CorPrimaria,
                unfocusedLabelColor = CorPrimaria,
                backgroundColor = Color.Transparent,
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth(.9F)
        )
        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {

                altura = when (altura.contains(',')) {
                    false -> altura
                    true -> altura.replace(',', '.')
                }

                peso = when (peso.contains(',')) {
                    false -> peso
                    true -> peso.replace(',', '.')
                }

                if (altura.isBlank() || altura.toDouble() <= 0) {
                    focus.moveFocus(FocusDirection.Previous)
                    return@Button
                }

                if (peso.isBlank() || peso.toDouble() <= 0) {
                    focus.moveFocus(FocusDirection.Previous)
                    return@Button
                }

                altura = when (altura.contains(',')) {
                    false -> altura
                    true -> altura.replace(',', '.')
                }

                viewModel.pegarResultado(altura.toDouble(), peso.toDouble())

                expand()
                altura = ""
                peso = ""
            },
            shape = RoundedCornerShape(9.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = CorPrimaria,
                contentColor = Color.Black
            ),
            modifier = Modifier
                .height(50.dp)
                .width(150.dp)
        ) {
            Text(text = "Calcular", fontSize = 18.sp)
        }
    }
}

@Composable
fun TelaResultado(resultado: Resultado) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.75F)
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(CorPrimaria),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SEU RESULTADO",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = resultado.imc,
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = resultado.classificacao,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun VerTela1() {
    //TelaPrincipal { }
}

@Preview(showBackground = true)
@Composable
fun VerTelaResultado() {
    TelaResultado(Resultado())
}