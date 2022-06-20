package com.litobumba.appimc

import org.junit.Assert.assertEquals
import org.junit.Test


class UseCasesTest {

    private val peso = 60.0
    private val altura = 1.75

    @Test
    fun calcularIMC() {
        val esperado = 19.591836734693878
        val resultado = UseCases().calcularIMC(altura, peso)
        assertEquals(esperado.toString(), resultado.toString())
    }

    @Test
    fun pegarClassificacao() {
        val imc = UseCases().calcularIMC(peso, altura)
        val esperado = Resultado(
            imc.toString().replace('.', ','),
            "NORMAL",
            "0"
        )
        val resultado = UseCases().pegarClassificacao(imc)
        assertEquals(esperado, resultado)
    }
}