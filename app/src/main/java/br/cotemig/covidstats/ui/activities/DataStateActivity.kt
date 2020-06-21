package br.cotemig.covidstats.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.cotemig.covidstats.R
import br.cotemig.covidstats.models.StatesCases
import br.cotemig.covidstats.services.RetrofitInitializer
import retrofit2.Call
import retrofit2.Response

class DataStateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_state)

        val estado: String = intent.getStringExtra("uf")
        getCases(estado)

    }


    fun getCases(uf: String) {
        var s = RetrofitInitializer().serviceCases()

        var call = s.getCasesState(uf)

        call.enqueue(object : retrofit2.Callback<StatesCases> {

            override fun onResponse(
                call: Call<StatesCases>?,
                response: Response<StatesCases>?
            ) {
                response?.let {
                    if (it.code() == 200) {

                        var data = it.body()
// .
                    }
                }
            }

            override fun onFailure(call: Call<StatesCases>?, t: Throwable?) {
                Toast.makeText(this@DataStateActivity, "Ops", Toast.LENGTH_LONG).show()
            }

        })

    }

    fun setupPieChartView() {

    }


}