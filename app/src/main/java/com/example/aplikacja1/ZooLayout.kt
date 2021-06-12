package com.example.aplikacja1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.content.Intent
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatImageButton
import java.lang.IllegalArgumentException
import java.util.ArrayList
import kotlin.random.Random

class ZooLayout : AppCompatActivity() {

    private var nazwaTrybu: TextView? = null;
    private var wyswietlanyTekst: TextView? = null;
    private var wykrzyknik: TextView? = null;

    //guziki do obslugiwania
    private var odtworz: AppCompatImageButton? =null;
    private var wstecz: AppCompatImageButton? =null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoo_layout)

        nazwaTrybu =findViewById(R.id.nazwaPojazdy)
        wyswietlanyTekst = findViewById(R.id.JakiPojazd_tekst)
        wykrzyknik = findViewById(R.id.JakiPojazd_tekst)

        odtworz =findViewById(R.id.odtwarzaj)
        wstecz = findViewById(R.id.powrot)
        val nazwy: Array<ImageButton> = arrayOf(
            findViewById(R.id.zoo_1) as ImageButton,
            findViewById(R.id.zoo_2) as ImageButton,
            findViewById(R.id.zoo_3) as ImageButton,
            findViewById(R.id.zoo_4) as ImageButton,
            findViewById(R.id.zoo_5) as ImageButton,
            findViewById(R.id.zoo_6) as ImageButton,
            findViewById(R.id.zoo_7) as ImageButton,
            findViewById(R.id.zoo_8) as ImageButton,
            findViewById(R.id.zoo_9) as ImageButton,
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
            var str = "zoo" + indeksy.get(index).toString()
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




    companion object {
        fun getResourceID(resName: String, resType: String?, ctx: Context): Int {
            val ResourceID = ctx.resources.getIdentifier(
                resName, resType,
                ctx.applicationInfo.packageName
            )
            return if (ResourceID == 0) {
                throw IllegalArgumentException(
                    "No resource string found with name $resName"
                )
            } else {
                ResourceID
            }
        }
    }
}