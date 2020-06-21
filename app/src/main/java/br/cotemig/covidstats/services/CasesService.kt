package br.cotemig.covidstats.services

import br.cotemig.covidstats.models.*
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.Query

interface CasesService {

    @GET("v2")
    fun getCasesStates(): Call<StatesCasesResponse>

    @GET("v1/brazil/uf/{ufid}")
    fun getCasesState(@Path("ufid") uf: String): Call<StatesCases>

    @GET("v1/countries")
    fun getCasesCountries(): Call<CountryCasesResponse>

    @GET("v1/brazil")
    fun getCasesBrazil(): Call<BrazilCasesResponse>

    @GET("PortalCasos")
    fun getCasesBrazilPerDay(): Call<BrazilPerDayCasesResponse>


}