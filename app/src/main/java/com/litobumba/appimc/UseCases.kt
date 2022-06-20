package com.litobumba.appimc

data class Resultado(
    val imc: String = "0",
    var classificacao: String = "",
    var grau: String = ""
)

class UseCases {

    fun calcularIMC(
        altura: Double,
        peso: Double
    ): Double {
        return (peso / (altura * altura))
    }

    fun pegarClassificacao(imc: Double): Resultado {

        val resultado = Resultado(imc = imc.toString().replace('.', ','))

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
}