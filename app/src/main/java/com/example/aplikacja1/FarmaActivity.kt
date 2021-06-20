package com.example.aplikacja1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import java.lang.IllegalArgumentException
import java.util.ArrayList
import kotlin.random.Random

class FarmaActivity : AppCompatActivity() {

    private var farma: TextView? = null;
    private var pytanie: TextView? = null;

    private var play: AppCompatImageButton? =null;
    private var wstecz: AppCompatImageButton? =null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farma)

        farma =findViewById(R.id.farma)
        pytanie = findViewById(R.id.farma_tekst)
        wstecz = findViewById(R.id.powrot4)

        val nazwy: Array<ImageButton> = arrayOf(
            findViewById(R.id.farm_1) as ImageButton,
            findViewById(R.id.farm_2) as ImageButton,
            findViewById(R.id.farm_3) as ImageButton,
            findViewById(R.id.farm_4) as ImageButton,
            findViewById(R.id.farm_5) as ImageButton,
            findViewById(R.id.farm_6) as ImageButton,
            findViewById(R.id.farm_7) as ImageButton,
            findViewById(R.id.farm_8) as ImageButton,
            findViewById(R.id.farm_9) as ImageButton,
        )
        val indeksy: ArrayList<Int> = ArrayList()
        val size = 10

        while(indeksy.count()<9) {
            var losuj = Random.nextInt(1,10)
            if (!indeksy.contains(losuj)) {
                indeksy.add(losuj);
            }
        }

        nazwy.forEachIndexed{index, it ->
            var str = "farm" + indeksy.get(index).toString()
            it.setImageDrawable(
                resources.getDrawable(
                    ZooLayout.Companion.getResourceID(
                        str, "drawable",
                        applicationContext
                    )
                )
            )
        }
        wstecz?.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?){
                openLayoutMain()
            }
        })


    }

    private fun openLayoutMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun openLayoutBrawo(){
        val intent = Intent(this, BrawoActivity::class.java)
        startActivity(intent)
    }

}