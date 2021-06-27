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
    private var play: AppCompatImageButton? =null;
    private var wstecz: AppCompatImageButton? =null;

    val obj = Funkcje() //obiekt klasy funkcje, abyśmy mogły wykorzystywać metody niestatyczne

    var czyLosowac = true
    var punkty = 0
    var losowe: ArrayList<Int> = ArrayList()
    var piosenki: ArrayList<String> = ArrayList()
    val indeksy: ArrayList<Int> = ArrayList()   //tablica do przechowywania indeksów losowanych obrazków


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pojazdy_layout)

        nazwaTrybu =findViewById(R.id.nazwaPojazdy)
        wyswietlanyTekst = findViewById(R.id.JakiPojazd_tekst)
        wykrzyknik = findViewById(R.id.JakiPojazd_tekst)

        //tablica, w której są przechowywane przyciski z obrazkami
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

        //losowanie indeksów w tablicy bez powtórzeń
        while(indeksy.count()<9) {
            var losuj = Random.nextInt(1,10)
            if (!indeksy.contains(losuj)) {
                indeksy.add(losuj);
            }
        }
        //pobieranie obrazka o odpowiedniej wartości indeksu z folderu drawable i dopisywanie do kolejnego przycisku
        nazwy.forEachIndexed{index, it ->
            var str = "poj" + indeksy.get(index).toString()
            it.setImageDrawable(
                resources.getDrawable(
                    obj.getResourceID(
                        str, "drawable",
                        applicationContext
                    )
                )
            )
        }

        play =findViewById(R.id.odtwarzaj2)
        wstecz = findViewById(R.id.powrot2)

        wstecz?.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?){
                openLayoutMain()
            }
        })
        play?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var dok = obj.losuj(czyLosowac, losowe, Nazwy.POJ2)
//                if(!piosenki.contains(dok)){
//
//                    val i = ImageView(getApplicationContext())
//                    i.setImageResource(R.drawable.dobrze)
//                    val toast = Toast(getApplicationContext())
//                    piosenki.add(dok)
//
//                }
                czyLosowac = false
                obj.playFromFirebase(Nazwy.POJ,dok,this@PojazdyLayout)
            }
        })

        for(i in nazwy.indices){
            nazwy[i].setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {


//                    //wyświetl emotke
//                    if (nazwy[i] == ?? ){
//                        val i = ImageView(getApplicationContext())
//                        i.setImageResource(R.drawable.dobrze)
//                        val toast = Toast(getApplicationContext())
//                        toast.setDuration(Toast.LENGTH_SHORT)
//                        toast.setGravity(Gravity.CENTER,0,0)
//                        toast.setView(i)
//                        toast.show()
//                    }
//                    else {
//                        val i = ImageView(getApplicationContext())
//                        i.setImageResource(R.drawable.zle)
//                        val toast = Toast(getApplicationContext())
//                        toast.setDuration(Toast.LENGTH_SHORT)
//                        toast.setGravity(Gravity.CENTER,0,0)
//                        toast.setView(i)
//                        toast.show()
//                    }
                    punkty+=1
                    czyLosowac=true
                }
            })
        }
    }

    private fun openLayoutMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}