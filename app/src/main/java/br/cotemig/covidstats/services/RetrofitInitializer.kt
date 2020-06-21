package br.cotemig.covidstats.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer {
    companion object {
        private val okHttpClient: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                    chain.proceed(newRequest.build())
                }
                .addInterceptor(HttpLoggingInterceptor().also { it ->
                    it.level = HttpLoggingInterceptor.Level.BODY
                })
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
        }

    }

    //Rota para estados brasileiros e dados gerais de paises
    private val covid19 = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://covid19-brazil-api.now.sh/api/report/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //Rota para estados brasileiros e dados gerais de paises
    private val covid19Gov = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://xx9p7hp1p7.execute-api.us-east-1.amazonaws.com/prod/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun serviceCases(): CasesService {
        return covid19.create(CasesService::class.java)
    }

    fun serviceCasesGov(): CasesService {
        return covid19Gov.create(CasesService::class.java)
    }


}