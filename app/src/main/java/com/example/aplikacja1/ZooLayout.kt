package com.example.aplikacja1

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.util.*
import kotlin.random.Random


class ZooLayout : AppCompatActivity() {

    private var nazwaTrybu: TextView? = null;
    private var wyswietlanyTekst: TextView? = null;
    private var wykrzyknik: TextView? = null;
    private val obj = Funkcje()

    //guziki do obslugiwania
    private var play: AppCompatImageButton? =null;
    private var wstecz: AppCompatImageButton? =null;

    var czyLosowac = true
    var punkty = 0
    var losowe: ArrayList<Int> = ArrayList()
    val indeksy: ArrayList<Int> = ArrayList()
    var piosenki: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoo_layout)

        nazwaTrybu =findViewById(R.id.nazwaPojazdy)
        wyswietlanyTekst = findViewById(R.id.JakiPojazd_tekst)
        wykrzyknik = findViewById(R.id.JakiPojazd_tekst)

        play =findViewById(R.id.odtwarzaj)
        wstecz = findViewById(R.id.powrot)


        val licznik = 0

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
                var dok = obj.losuj(czyLosowac, losowe,Nazwy.ZOO)
//                if(!piosenki.contains(dok)){
//
//                    val i = ImageView(getApplicationContext())
//                    i.setImageResource(R.drawable.dobrze)
//                    val toast = Toast(getApplicationContext())
//                    piosenki.add(dok)
//
//                }
                czyLosowac = false
                obj.playFromFirebase(Nazwy.ZOO,dok,this@ZooLayout)
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