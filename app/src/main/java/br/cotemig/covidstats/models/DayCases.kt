package br.cotemig.covidstats.models

data class DayCases(
    var _id: String = "",
    var casosAcumulado: Int = 0,
    var obitosAcumulado: Int = 0,
    var casosNovos: Int = 0,
    var obitosNovos: Int = 0
)