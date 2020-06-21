package br.cotemig.covidstats.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.cotemig.covidstats.R
import br.cotemig.covidstats.models.CountryCases
import br.cotemig.covidstats.ui.adapters.CountriesCasesAdapter
import kotlinx.android.synthetic.main.activity_country_list.*
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import br.cotemig.covidstats.models.CountryCasesResponse
import br.cotemig.covidstats.services.RetrofitInitializer
import br.cotemig.covidstats.ui.adapters.StatesCasesAdapter
import retrofit2.Call
import retrofit2.Response

class CountryListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_list)
        getCases()

    }
    fun getCases(){
    }

    private fun initList(arr: List<CountryCases>) {
        listaPorPais.adapter = CountriesCasesAdapter(this@CountryListActivity, arr)
    }


}
