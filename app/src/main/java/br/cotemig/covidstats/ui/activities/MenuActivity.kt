package br.cotemig.covidstats.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.cotemig.covidstats.R
import br.cotemig.covidstats.ui.fragments.BrasilFragment
import br.cotemig.covidstats.ui.fragments.CountriesListFragment
import br.cotemig.covidstats.ui.fragments.StatesListFragment
import kotlinx.android.synthetic.main.activity_menu.*


class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.content, BrasilFragment(), "Dados Brasil")
        ft.commit()

        estadosBrasileiros.setOnClickListener {
            setFragment(StatesListFragment(),"EstadosBrasil")
        }

        paises.setOnClickListener {
            setFragment(CountriesListFragment(),"PaisesMundo")
        }

    }

    fun setFragment(f: Fragment, name: String) {
        // iniciando a transação para trocar de tela (fragment)
        val ft = supportFragmentManager.beginTransaction()
        // trocando o fragment f com nome name no content
        ft.replace(R.id.content, f, name)
        // adicionando fragment na pilha de "voltar"
        ft.addToBackStack(name)
        // confirmando a troca de fragment
        ft.commit()
    }

}