package br.cotemig.covidstats.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.cotemig.covidstats.R
import br.cotemig.covidstats.models.CountryCases
import br.cotemig.covidstats.models.OneCountryCaseResponse
import br.cotemig.covidstats.models.StatesCases
import br.cotemig.covidstats.services.RetrofitInitializer
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.formatter.PercentFormatter
import kotlinx.android.synthetic.main.activity_data_country.*
import kotlinx.android.synthetic.main.activity_data_state.*
import kotlinx.android.synthetic.main.activity_data_state.nome
import kotlinx.android.synthetic.main.activity_data_state.recuperadoss
import kotlinx.android.synthetic.main.activity_data_state.suspeitos
import retrofit2.Call
import retrofit2.Response

class DataCountryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_country)

        val pais: String = intent.getStringExtra("pais")
        getCases(pais)

    }


    fun getCases(pais: String) {
        var s = RetrofitInitializer().serviceCases()

        var call = s.getCasesCountry(pais)

        call.enqueue(object : retrofit2.Callback<OneCountryCaseResponse> {

            override fun onResponse(
                call: Call<OneCountryCaseResponse>?,
                response: Response<OneCountryCaseResponse>?
            ) {
                response?.let {
                    if (it.code() == 200) {

                        recuperadoss.text = "Recuperados " + it.body().data.recovered.toString()
                        confirmados.text = "Confirmados " + it.body().data.confirmed.toString()
                        nome.text =  it.body().data.country.toString()
                        var atualizadoEm = "Ultima data: " + it.body().data.updated_at.toString()


                        val NoOfEmp= ArrayList<Entry>()

                        val pDead = (it.body().data.deaths * 100 ) / it.body().data.confirmed
                        val pRec = (it.body().data.recovered * 100 ) / it.body().data.confirmed
                        val pTot = 100  - pDead - pRec

                        NoOfEmp.add(Entry(pDead.toFloat(), 0))
                        NoOfEmp.add(Entry(pRec.toFloat(), 1))
                        NoOfEmp.add(Entry(pTot.toFloat(), 2))

                        val lb= ArrayList<String>()
                        lb.add("Mortos")
                        lb.add("Recuperados")
                        lb.add("Ativos")


                        val colorsList = ArrayList<Int>()
                        colorsList.add(R.color.red)
                        colorsList.add(R.color.verdeSucesso)
                        colorsList.add(R.color.grey)

                        var dataSet = PieDataSet(NoOfEmp, atualizadoEm)
                        var data = PieData(lb, dataSet)

                        data.setValueTextColor(R.color.colorPrimaryDark)
                        data.setValueTextSize(20f)
                        dataSet.setColors(colorsList)

                        dataSet.setValueFormatter( PercentFormatter() )
                        dataSet.setValueTextColor(R.color.colorPrimaryDark);
                        dataSet.isHighlightEnabled = true
                        dataSet.sliceSpace = 10f //Margem entre pies do gráfico

                        porcentagemPaisChart.data = data
                        porcentagemPaisChart.animateXY(2000, 2000)
                        porcentagemPaisChart.data = data
                        porcentagemPaisChart.legend.isEnabled = false //Removendo legenda
                        porcentagemPaisChart.isDrawHoleEnabled = true //Exibe o círculo no centro do gráfico
                        porcentagemPaisChart.holeRadius = 20f //Tamanho do círculo
                        porcentagemPaisChart.transparentCircleRadius = 60f //Tamanho do círculo (parte transparente)

                    }
                }
            }

            override fun onFailure(call: Call<OneCountryCaseResponse>?, t: Throwable?) {
                Toast.makeText(this@DataCountryActivity, "Ops", Toast.LENGTH_LONG).show()
            }

        })

    }


}