package br.cotemig.covidstats.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AlertDialog
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
        val sharedPreferences = getSharedPreferences(packageName + "_preferences", Context.MODE_PRIVATE)
        val tutorialValue = sharedPreferences.getBoolean("hasSeenTutorial", false)

        if (tutorialValue === false) {
            var intentTutorial = Intent(this@MainActivity, TutorialActivity::class.java)
            startActivity(intentTutorial)
        } else {
            var intentMenu = Intent(this@MainActivity, MenuActivity::class.java)
            startActivity(intentMenu)
        }
        finish()
    }
}
