package com.example.aplikacja1

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatImageButton
import java.lang.IllegalArgumentException
import java.util.ArrayList
import kotlin.random.Random

class FarmaActivity : AppCompatActivity() {

    private var farma: TextView? = null;
    private var pytanie: TextView? = null;

    private var play: AppCompatImageButton? =null;
    private var wstecz: AppCompatImageButton? =null;
    private val obj = Funkcje()

    var czyLosowac = true
    var punkty = 0
    var losowe: ArrayList<Int> = ArrayList()
    val indeksy: ArrayList<Int> = ArrayList()
    var piosenki: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farma)

        farma =findViewById(R.id.farma)
        pytanie = findViewById(R.id.farma_tekst)
        wstecz = findViewById(R.id.powrot4)
        play = findViewById(R.id.odtwarzaj4)

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
                    obj.getResourceID(
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

        play?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var dok = obj.losuj(czyLosowac, losowe, Nazwy.FAR2)
//                if(!piosenki.contains(dok)){
//
//                    val i = ImageView(getApplicationContext())
//                    i.setImageResource(R.drawable.dobrze)
//                    val toast = Toast(getApplicationContext())
//                    piosenki.add(dok)
//
//                }
                czyLosowac = false
                obj.playFromFirebase(Nazwy.FAR1,dok,this@FarmaActivity)
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

    private fun openLayoutBrawo(){
        val intent = Intent(this, BrawoActivity::class.java)
        startActivity(intent)
    }

}