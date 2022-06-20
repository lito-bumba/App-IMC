package com.litobumba.appimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.litobumba.appimc.ui.TelaPrincipal
import com.litobumba.appimc.ui.TelaResultado
import com.litobumba.appimc.ui.theme.AppIMCTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<AppViewModel>()

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppIMCTheme {
                val sheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
                val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
                val scope = rememberCoroutineScope()
                val resultado: Resultado by viewModel.resultado.observeAsState(Resultado())

                BottomSheetScaffold(
                    scaffoldState = scaffoldState,
                    sheetContent = {
                        TelaResultado(resultado)
                    },
                    sheetPeekHeight = 0.dp,
                    backgroundColor = Color.Transparent
                ) {

                    TelaPrincipal(viewModel) {
                        scope.launch {
                            if (sheetState.isCollapsed)
                                sheetState.expand()
                        }
                    }
                }
            }
        }
    }

    private var counter = 0
    override fun onBackPressed() {
        counter++
        if (counter == 2)
            super.onBackPressed()
    }
}