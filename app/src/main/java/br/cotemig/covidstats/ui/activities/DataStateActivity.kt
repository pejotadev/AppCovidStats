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
                        var atualizadoEm = "Ultima atualização " + it.body().datetime.toString()


                        val NoOfEmp= ArrayList<Entry>()

                        val pDead = (it.body().deaths * 100 ) / it.body().cases
                        //val pRec = (it.body().refuses * 100 ) / it.body().cases
                        val pTot = 100  - pDead

                        NoOfEmp.add(Entry(pDead.toFloat(), 0))
                        //NoOfEmp.add(Entry(pRec.toFloat(), 1))
                        NoOfEmp.add(Entry(pTot.toFloat(), 2))


                        val dataSet = PieDataSet(NoOfEmp, atualizadoEm)

                        val lb= ArrayList<String>()
                        lb.add("Mortos")
                        //year.add("Recuperados")
                        lb.add("Ativos / recuperados")

                        val colors = ArrayList<Int>()
                        colors.add(R.color.red)
                        //colors.add(R.color.verdeSucesso)
                        colors.add(R.color.grey)
                        dataSet.setColors(colors)

                        val data = PieData(lb, dataSet)

                        data.setValueTextColor(R.color.colorPrimaryDark);
                        dataSet.setValueFormatter( PercentFormatter() )
                        dataSet.setValueTextColor(R.color.colorPrimaryDark);

                        porcentagemEstadoChart.data = data
//        dataSet?.setColors(*ColorTemplate.COLORFUL_COLORS)
                        porcentagemEstadoChart.animateXY(5000, 2000)

                    }
                }
            }

            override fun onFailure(call: Call<StatesCases>?, t: Throwable?) {
                Toast.makeText(this@DataStateActivity, "Ops", Toast.LENGTH_LONG).show()
            }

        })

    }


}