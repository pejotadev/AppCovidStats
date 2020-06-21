package br.cotemig.covidstats.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import br.cotemig.covidstats.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            initApp()
        }, 2000)
    }

    fun initApp(){
        var intent = Intent(this@MainActivity, TutorialActivity::class.java)
        startActivity(intent)
        finish()
    }
}
