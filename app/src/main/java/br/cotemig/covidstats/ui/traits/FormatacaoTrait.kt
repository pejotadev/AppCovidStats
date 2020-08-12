package br.cotemig.covidstats.ui.traits

import java.text.NumberFormat
import java.util.*

class FormatacaoTrait (){
    var numberFormat = NumberFormat.getNumberInstance(Locale("pt","BR"))

    fun formatarNumero(numero: Int): String{
        var numeroFormatado = numberFormat.format(numero)
        return numeroFormatado.toString()
    }

}
