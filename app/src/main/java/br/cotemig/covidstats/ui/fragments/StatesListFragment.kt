package br.cotemig.covidstats.ui.fragments



import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.cotemig.covidstats.R
import br.cotemig.covidstats.models.StatesCases
import br.cotemig.covidstats.models.StatesCasesResponse
import br.cotemig.covidstats.services.RetrofitInitializer
import br.cotemig.covidstats.ui.activities.DataStateActivity
import br.cotemig.covidstats.ui.activities.MenuActivity
import br.cotemig.covidstats.ui.activities.TutorialActivity
import br.cotemig.covidstats.ui.adapters.StatesCasesAdapter
import kotlinx.android.synthetic.main.fragment_list_states.*
import retrofit2.Call
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [StatesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatesListFragment : Fragment() {
     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getCases()
        return inflater.inflate(R.layout.fragment_list_states, container, false)
    }

    fun getCases() {
        var s = RetrofitInitializer().serviceCases()
        var activity = context as MenuActivity


            var call = s.getCasesStates()

            call.enqueue(object : retrofit2.Callback<StatesCasesResponse> {

                override fun onResponse(
                    call: Call<StatesCasesResponse>?,
                    response: Response<StatesCasesResponse>?
                ) {
                    response?.let {
                        if (it.code() == 200) {

                            var list = it.body().data
                            var myList = ArrayList<StatesCases>()
                            myList.addAll(list)

                            initListEstados(myList)

                            listviewlayout.setOnItemClickListener{ parent, view, position, id ->

                                var intent = Intent(activity, DataStateActivity::class.java)
                                intent.putExtra("uf", myList[position].uf)
                                startActivity(intent)
                            }


                            pesquisarEstado.addTextChangedListener(object : TextWatcher {

                                override fun afterTextChanged(s: Editable) {}

                                override fun beforeTextChanged(
                                    s: CharSequence,
                                    start: Int,
                                    count: Int,
                                    after: Int
                                ) {
                                }

                                override fun onTextChanged(
                                    s: CharSequence,
                                    start: Int,
                                    before: Int,
                                    count: Int
                                ) {

                                    var newList = ArrayList<StatesCases>()
                                    var searched = s.toString().toLowerCase()

                                    for (item in list)
                                        if (item.state.toLowerCase().contains(searched))
                                            newList.add(item)

                                    myList.clear()
                                    myList.addAll(newList)
                                    initListEstados(myList)

                                }
                            })
                        }
                    }
                }

                override fun onFailure(call: Call<StatesCasesResponse>?, t: Throwable?) {
                    Toast.makeText(activity, "Ops", Toast.LENGTH_LONG).show()
                }

            })

    }

    private fun initListEstados(arr: List<StatesCases>) {
        var activity = context as MenuActivity
        listviewlayout.adapter = StatesCasesAdapter(activity, arr)
    }
}