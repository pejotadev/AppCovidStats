package br.cotemig.covidstats.ui.fragments



import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.cotemig.covidstats.R
import br.cotemig.covidstats.models.CountryCases
import br.cotemig.covidstats.models.CountryCasesResponse
import br.cotemig.covidstats.services.RetrofitInitializer
import br.cotemig.covidstats.ui.activities.MenuActivity
import br.cotemig.covidstats.ui.adapters.CountriesCasesAdapter
import kotlinx.android.synthetic.main.fragment_list_countries.*
import kotlinx.android.synthetic.main.fragment_list_states.*
import retrofit2.Call
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [CountriesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CountriesListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getCases()
        return inflater.inflate(R.layout.fragment_list_countries, container, false)
    }

    fun getCases() {
        var activity = context as MenuActivity

            var s = RetrofitInitializer().serviceCases()

            var call = s.getCasesCountries()

            call.enqueue(object : retrofit2.Callback<CountryCasesResponse>{

                override fun onResponse(
                    call: Call<CountryCasesResponse>?,
                    response: Response<CountryCasesResponse>?
                ) {
                    response?.let{
                        if(it.code() == 200){

                            var list = it.body().data
                            var myList = ArrayList<CountryCases>()
                            myList.addAll(list)

                            initListPais(myList)

                            listviewlayoutC.setOnItemClickListener{ parent, view, position, id ->
                                var x = myList[position]
                            }

                            pesquisarPais.addTextChangedListener(object : TextWatcher {

                                override fun afterTextChanged(s: Editable) {}

                                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

                                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                                    var newList = ArrayList<CountryCases>()
                                    var searched = s.toString().toLowerCase()

                                    for(item in list)
                                        if(item.country.toLowerCase().contains(searched))
                                            newList.add(item)

                                    myList.clear()
                                    myList.addAll(newList)
                                    initListPais(myList)

                                }
                            })


                        }
                    }
                }

                override fun onFailure(call: Call<CountryCasesResponse>?, t: Throwable?) {
                    Toast.makeText(activity, "Ops", Toast.LENGTH_LONG).show()
                }

            })
        }
    private fun initListPais(arr: List<CountryCases>) {
        var activity = context as MenuActivity
        listviewlayoutC.adapter = CountriesCasesAdapter(activity, arr)
    }
}