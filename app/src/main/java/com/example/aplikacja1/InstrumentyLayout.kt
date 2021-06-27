package com.example.aplikacja1

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import java.util.ArrayList
import kotlin.random.Random

class InstrumentyLayout : AppCompatActivity() {

    private var instrumenty: TextView? = null;
    private var pytanie: TextView? = null;

    private var play: AppCompatImageButton? = null;
    private var wstecz: AppCompatImageButton? = null;

    var losowe: ArrayList<Int> = ArrayList()
    val indeksy: ArrayList<Int> = ArrayList()
    var piosenki: ArrayList<String> = ArrayList()

    val obj = Funkcje()
    var czyLosowac = true
    var punkty = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instrumenty_layout)

        instrumenty = findViewById(R.id.instrumenty)
        pytanie = findViewById(R.id.instrumenty_tekst)

        play = findViewById(R.id.odtwarzaj3)
        wstecz = findViewById(R.id.powrot3)

        val nazwy: Array<ImageButton> = arrayOf(
            findViewById(R.id.inst_1) as ImageButton,
            findViewById(R.id.inst_2) as ImageButton,
            findViewById(R.id.inst_3) as ImageButton,
            findViewById(R.id.inst_4) as ImageButton,
            findViewById(R.id.inst_5) as ImageButton,
            findViewById(R.id.inst_6) as ImageButton,
            findViewById(R.id.inst_7) as ImageButton,
            findViewById(R.id.inst_8) as ImageButton,
            findViewById(R.id.inst_9) as ImageButton,
        )

        while (indeksy.count() < 9) {
            var losuj = Random.nextInt(1, 10)
            if (!indeksy.contains(losuj)) {
                indeksy.add(losuj);
            }
        }
        nazwy.forEachIndexed { index, it ->
            var str = "inst" + indeksy.get(index).toString()
            it.setImageDrawable(
                resources.getDrawable(
                    obj.getResourceID(
                        str, "drawable",
                        applicationContext
                    )
                )
            )
        }

        wstecz?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                openLayoutMain()
            }
        })

        play?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                var obj = Funkcje()
                var dok = obj.losuj(czyLosowac, losowe, Nazwy.SO)
                if(!piosenki.contains(dok)){

                    val i = ImageView(getApplicationContext())
                    i.setImageResource(R.drawable.dobrze)
                    val toast = Toast(getApplicationContext())
                    piosenki.add(dok)

                }
                czyLosowac = false
                obj.playFromFirebase(Nazwy.INST,dok,this@InstrumentyLayout)
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

    private fun openLayoutMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}


