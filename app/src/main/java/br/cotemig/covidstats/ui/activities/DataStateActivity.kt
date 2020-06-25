package br.cotemig.covidstats.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.cotemig.covidstats.R
import br.cotemig.covidstats.models.StatesCases
import br.cotemig.covidstats.services.RetrofitInitializer
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.formatter.PercentFormatter
import kotlinx.android.synthetic.main.activity_data_state.*
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

                        recuperadoss.text = "Recuperados " + it.body().refuses.toString()
                        nome.text =  it.body().state.toString()
                        suspeitos.text = "Suspeitos: " + it.body().suspects.toString()
                        val atualizadoEm = "Ultima atualização " + it.body().datetime.toString()
                        val pDead = (it.body().deaths * 100 ) / it.body().cases
                        //val pRec = (it.body().refuses * 100 ) / it.body().cases
                        val pTot = 100  - pDead

                        val NoOfEmp= ArrayList<Entry>()
                        NoOfEmp.add(Entry(pDead.toFloat(), 0))
                        //NoOfEmp.add(Entry(pRec.toFloat(), 1))
                        NoOfEmp.add(Entry(pTot.toFloat(), 2))
                        val lb= ArrayList<String>()
                        lb.add("Mortos")
                        //year.add("Recuperados")
                        lb.add("Ativos / recuperados")
                        val colorsList = ArrayList<Int>()
                        colorsList.add(R.color.design_default_color_error)
                        //colorsList.add(R.color.verdeSucesso)
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

                        porcentagemEstadoChart.data = data
                        porcentagemEstadoChart.animateXY(2000, 2000)
                        porcentagemEstadoChart.data = data
                        porcentagemEstadoChart.legend.isEnabled = false //Removendo legenda
                        porcentagemEstadoChart.isDrawHoleEnabled = true //Exibe o círculo no centro do gráfico
                        porcentagemEstadoChart.holeRadius = 20f //Tamanho do círculo
                        porcentagemEstadoChart.transparentCircleRadius = 60f //Tamanho do círculo (parte transparente)


                    }
                }
            }

            override fun onFailure(call: Call<StatesCases>?, t: Throwable?) {
                Toast.makeText(this@DataStateActivity, "Ops", Toast.LENGTH_LONG).show()
            }

        })

    }


}