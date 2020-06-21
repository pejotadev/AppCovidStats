package br.cotemig.covidstats.models

data class CountryCases(
    var country: String = "",
    var cases: Int = 0,
    var confirmed: Int = 0,
    var deaths: Int = 0,
    var recovered: Int = 0
)