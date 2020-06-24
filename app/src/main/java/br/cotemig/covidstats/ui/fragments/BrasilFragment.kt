package br.cotemig.covidstats.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.cotemig.covidstats.R
import br.cotemig.covidstats.models.BrazilCasesResponse
import br.cotemig.covidstats.models.BrazilPerDayCasesResponse
import br.cotemig.covidstats.services.RetrofitInitializer
import br.cotemig.covidstats.ui.activities.MenuActivity
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.activity_data_state.*
import kotlinx.android.synthetic.main.fragment_brasil.*
import retrofit2.Call
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class BrasilFragment : Fragment() {
    //var activity = context as MenuActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getCases()
        setBarChartDeads()

        return inflater.inflate(R.layout.fragment_brasil, container, false)
    }

    fun getCases(){
        var activity = context as MenuActivity
        var s = RetrofitInitializer().serviceCases()

        var call = s.getCasesBrazil()

        call.enqueue(object : retrofit2.Callback<BrazilCasesResponse>{

            override fun onResponse(
                call: Call<BrazilCasesResponse>?,
                response: Response<BrazilCasesResponse>?
            ) {
                response?.let{
                    if(it.code() == 200){

                        recuperados.text = it.body().data.recovered.toString()
                        ativos.text = it.body().data.cases.toString()
                        mortesTotal.text = it.body().data.deaths.toString()
                        getDeathsLastDay()

                    }
                }
            }

            override fun onFailure(call: Call<BrazilCasesResponse>?, t: Throwable?) {
                Toast.makeText(activity, "Ops", Toast.LENGTH_LONG).show()
            }

        })
    }

    fun getDeathsLastDay(){
        var s = RetrofitInitializer().serviceCasesGov()

        var call = s.getCasesBrazilPerDay()

        call.enqueue(object : retrofit2.Callback<BrazilPerDayCasesResponse>{

            override fun onResponse(
                call: Call<BrazilPerDayCasesResponse>?,
                response: Response<BrazilPerDayCasesResponse>?
            ) {
                response?.let{
                    if(it.code() == 200){
                        var lastDay = it.body().dias[it.body().dias.size - 1]
                        mortesDia.text = lastDay.obitosNovos.toString()
                    }
                }
            }

            override fun onFailure(call: Call<BrazilPerDayCasesResponse>?, t: Throwable?) {
                Toast.makeText(activity, "Ops", Toast.LENGTH_LONG).show()
            }

        })
    }

    fun setBarChartDeads(){
        var s = RetrofitInitializer().serviceCasesGov()

        var call = s.getCasesBrazilPerDay()

        call.enqueue(object : retrofit2.Callback<BrazilPerDayCasesResponse>{

            override fun onResponse(
                call: Call<BrazilPerDayCasesResponse>?,
                response: Response<BrazilPerDayCasesResponse>?
            ) {
                response?.let{
                    if(it.code() == 200){
                        val entries = ArrayList<BarEntry>()
                        val barDataSet = BarDataSet(entries, "Mortes Acumuladas")
                        val labels = ArrayList<String>()
                        it.body().dias.forEachIndexed { index, element ->
                            entries.add(BarEntry(element.obitosAcumulado.toFloat(), index))
                            labels.add(element._id)
                        }

                        val data = BarData(labels, barDataSet)
                        barDiaryChart.data = data // set the data and list of lables into chart

                        barDiaryChart.setDescription("Brasil")  // set the description

                        //barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
                        barDataSet.color = resources.getColor(R.color.red)

                        barDiaryChart.animateY(3000)

                        loading.visibility = View.GONE

                    }
                }
            }

            override fun onFailure(call: Call<BrazilPerDayCasesResponse>?, t: Throwable?) {
                Toast.makeText(activity, "Ops", Toast.LENGTH_LONG).show()
            }

        })
    }

    fun setBarChartRefuses(){
        var s = RetrofitInitializer().serviceCasesGov()

        var call = s.getCasesBrazilPerDay()

        call.enqueue(object : retrofit2.Callback<BrazilPerDayCasesResponse>{

            override fun onResponse(
                call: Call<BrazilPerDayCasesResponse>?,
                response: Response<BrazilPerDayCasesResponse>?
            ) {
                response?.let{
                    if(it.code() == 200){
                        val entries = ArrayList<BarEntry>()
                        val barDataSet = BarDataSet(entries, "Casos Acumuladas")
                        val labels = ArrayList<String>()
                        it.body().dias.forEachIndexed { index, element ->
                            entries.add(BarEntry(element.casosAcumulado.toFloat(), index))
                            labels.add(element._id)
                        }

                        val data = BarData(labels, barDataSet)
                        barDiaryChart.data = data // set the data and list of lables into chart

                        barDiaryChart.setDescription("Brazil")  // set the description

                        //barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
                        barDataSet.color = resources.getColor(R.color.red)

                        barDiaryChart.animateY(3000)

                        loading.visibility = View.GONE

                    }
                }
            }

            override fun onFailure(call: Call<BrazilPerDayCasesResponse>?, t: Throwable?) {
                Toast.makeText(activity, "Ops", Toast.LENGTH_LONG).show()
            }

        })
    }


}