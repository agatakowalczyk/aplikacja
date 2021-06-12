package com.example.aplikacja1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import java.lang.IllegalArgumentException
import java.util.ArrayList
import kotlin.random.Random

class PojazdyLayout : AppCompatActivity() {

    private var nazwaTrybu: TextView? = null;
    private var wykrzyknik: TextView? = null;
    private var wyswietlanyTekst: TextView? = null;
    //guziki do obslugiwania apki
    private var odtworz: AppCompatImageButton? =null;
    private var wstecz: AppCompatImageButton? =null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pojazdy_layout)

        nazwaTrybu =findViewById(R.id.nazwaPojazdy)
        wyswietlanyTekst = findViewById(R.id.JakiPojazd_tekst)
        wykrzyknik = findViewById(R.id.JakiPojazd_tekst)

        val nazwy: Array<ImageButton> = arrayOf(
            findViewById(R.id.pojazd_1) as ImageButton,
            findViewById(R.id.pojazd_2) as ImageButton,
            findViewById(R.id.pojazd_3) as ImageButton,
            findViewById(R.id.pojazd_4) as ImageButton,
            findViewById(R.id.pojazd_5) as ImageButton,
            findViewById(R.id.pojazd_6) as ImageButton,
            findViewById(R.id.pojazd_7) as ImageButton,
            findViewById(R.id.pojazd_8) as ImageButton,
            findViewById(R.id.pojazd_9) as ImageButton,
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
            var str = "poj" + indeksy.get(index).toString()
            it.setImageDrawable(
                resources.getDrawable(
                    ZooLayout.Companion.getResourceID(
                        str, "drawable",
                        applicationContext
                    )
                )
            )
        }

        odtworz =findViewById(R.id.odtwarzaj2)
        wstecz = findViewById(R.id.powrot2)

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
        protected fun getResourceID(resName: String, resType: String?, ctx: Context): Int {
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