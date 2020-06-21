package br.cotemig.covidstats.ui.activities


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import br.cotemig.covidstats.R
import br.cotemig.covidstats.helpers.LinePagerIndicatorDecoration
import br.cotemig.covidstats.models.TutorialValues
import br.cotemig.covidstats.ui.adapters.TutorialAdapter
import kotlinx.android.synthetic.main.activity_tutorial.*

class TutorialActivity : AppCompatActivity(), LinePagerIndicatorDecoration.OnViewPosition {
    var imagens = ArrayList<TutorialValues>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        var imagens = ArrayList<TutorialValues>()
        imagens.add(TutorialValues("Use sempre uma mascara limpa", "https://images.vexels.com/media/users/3/193239/isolated/preview/e83db85552ee35f6276411c9f19d982d-covid-19-menino-personagem---cone-by-vexels.png"))
        imagens.add(TutorialValues("Sempre lave as mãos","https://images.vexels.com/media/users/3/193288/isolated/preview/b2a7f2fa70e9452b2f4ae832c1b651a2-covid-19-lavar-as-m--os---cone-by-vexels.png"))
        imagens.add(TutorialValues("Cuidado com a higiene e com o proximo é fundamental","https://images.vexels.com/media/users/3/193278/isolated/preview/b6205ac2d8fd90c8ac551b3f41f1302f-cotovelo-tosse-covarde-com-19-sintomas-by-vexels.png"))

        // RecyclerView
        lista.adapter = TutorialAdapter(this@TutorialActivity, imagens)
        lista.layoutManager = LinearLayoutManager(
            this@TutorialActivity,
            LinearLayoutManager.HORIZONTAL, false
        )

        lista.addItemDecoration(LinePagerIndicatorDecoration(this))

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(lista)

        btnContinue.setOnClickListener {
            gotoMenu()
        }

    }

    private fun gotoMenu() {

        var intent = Intent(this@TutorialActivity, MenuActivity::class.java)
        startActivity(intent)
    }

    override fun onViewPosition(position: Int) {
        if(position == 2){
            btnContinue.visibility = View.VISIBLE
        }else{
            btnContinue.visibility = View.GONE
        }
    }



}