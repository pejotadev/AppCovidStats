package br.cotemig.covidstats.ui.traits

import java.text.NumberFormat
import java.util.*

public class FormatacaoTrait (){

    fun formatarNumero(numero: Int): String{
        var numberFormat = NumberFormat.getNumberInstance(Locale("pt","BR"))
        var numeroFormatado = numberFormat.format(numero)
        return numeroFormatado.toString()
    }

}
