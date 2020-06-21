package br.cotemig.covidstats.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.cotemig.covidstats.R
import br.cotemig.covidstats.models.CountryCases
import br.cotemig.covidstats.models.StatesCases

class CountriesCasesAdapter (var context: Context, var list: List<CountryCases>) : BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = LayoutInflater.from(context).inflate(R.layout.item_states_cases, null)

        var nome = view.findViewById<TextView>(R.id.nome)
        var total = view.findViewById<TextView>(R.id.total)
        var mortes = view.findViewById<TextView>(R.id.mortos)
        var recuperados = view.findViewById<TextView>(R.id.recuperados)

        nome.text = list[position].country
        mortes.text = list[position].deaths.toString()
        recuperados.text = list[position].recovered.toString()

        return view

    }

    override fun getItem(position: Int): Any {
        return ""
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return  list.size
    }

}