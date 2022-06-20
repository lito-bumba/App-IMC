package com.litobumba.appimc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel : ViewModel() {

    private var _resultado = MutableLiveData<Resultado>()
    var resultado: LiveData<Resultado> = _resultado

    fun pegarResultado(altura: Double, peso: Double) {
        val imc = UseCases().calcularIMC(altura, peso)
        _resultado.value = UseCases().pegarClassificacao(imc)
    }

}