package com.litobumba.appimc

import org.junit.Assert.assertEquals
import org.junit.Test


class UseCasesTest {

    val peso = 60.0
    val altura = 1.75

    @Test
    fun calcularIMC() {
        val esperado = 19.59
        val resultado = UseCases().CalcularIMC(peso, altura)
        assertEquals(esperado.toString(), resultado.toString())
    }

    @Test
    fun pegarClassificacao() {
        val imc = UseCases().CalcularIMC(peso, altura)
        val esperado = "NORMAL"
        val resultado = UseCases().PegarClassificacao(imc).classificacao
        assertEquals(esperado, resultado)
    }
}