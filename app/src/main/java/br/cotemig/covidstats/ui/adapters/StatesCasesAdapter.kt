package br.cotemig.covidstats.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.cotemig.covidstats.R
import br.cotemig.covidstats.models.StatesCases
import br.cotemig.covidstats.ui.traits.FormatacaoTrait

class StatesCasesAdapter (var context: Context, var list: List<StatesCases>) : BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = LayoutInflater.from(context).inflate(R.layout.item_cases, null)
        var numberFormat = FormatacaoTrait()

        var nome = view.findViewById<TextView>(R.id.nome)
        var total = view.findViewById<TextView>(R.id.total)
        var mortes = view.findViewById<TextView>(R.id.mortos)
        var recuperados = view.findViewById<TextView>(R.id.recuperados)

        nome.text = list[position].state
        mortes.text = numberFormat.formatarNumero(list[position].deaths)
        recuperados.text = numberFormat.formatarNumero(list[position].refuses)

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