package com.litobumba.appimc

import kotlin.math.roundToInt

data class Resultado(
    val imc: Double = 0.0,
    var classificacao: String = "",
    var grau: String = ""
)

class UseCases {

    fun CalcularIMC(peso: Double, altura: Double): Double {
        val imc = (peso / (altura * altura))

        var num = imc.toString().substring(0, imc.toString().indexOf('.'))
        var dec = imc.toString().substring(imc.toString().indexOf('.'))
        dec = dec.substring(0, 3)

        return (num + dec).toDouble()
    }

    fun PegarClassificacao(imc: Double): Resultado {

        var resultado = Resultado(imc = imc)

        if (imc < 18.5) {
            resultado.classificacao = "MAGREZA"
            resultado.grau = "0"
        }

        if (imc in 18.5..24.9) {
            resultado.classificacao = "NORMAL"
            resultado.grau = "0"
        }

        if (imc in 25.0..29.9) {
            resultado.classificacao = "SOBREPESO"
            resultado.grau = "I"
        }

        if (imc in 30.0..39.9) {
            resultado.classificacao = "OBESIDADE"
            resultado.grau = "II"
        }

        if (imc > 40.0) {
            resultado.classificacao = "OBESIDADE GRAVE"
            resultado.grau = "III"
        }

        return resultado
    }

    fun pegarDuasCasasDecimais(numero: Double): Double {

        var num = numero.toString()
        num = num.substring(
            num.indexOf('.'),
            2
        )

        return 54.4
    }
}