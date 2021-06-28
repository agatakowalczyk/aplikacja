package com.example.aplikacja1

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.net.toUri
import com.google.firebase.firestore.FirebaseFirestore
import java.io.IOException
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

    private val obj = Funkcje() //obiekt klasy funkcje, abyśmy mogły wykorzystywać metody niestatyczne

    private var licznik = 0
    private var id = 0
    private var czyLosowac = true
    private var punkty = 0
    private var losowe: ArrayList<Int> = ArrayList()
    private val indeksy: ArrayList<Int> = ArrayList()   //tablica do przechowywania indeksów losowanych obrazków
    private var piosenki: ArrayList<String> = ArrayList()

    var mediaPlayer: MediaPlayer? = null

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
                if (!piosenki.contains(dok)) {
                    piosenki.add(dok)
                }
                czyLosowac = false

                val mFireStore = FirebaseFirestore.getInstance()
                var docRef = mFireStore.collection(Nazwy.POJ).document(dok)
                docRef.get()
                    .addOnSuccessListener() { document ->
                        if (document != null) {
                            var piosenka = document.toObject(Song::class.java)!!
                            id = piosenka.mediaId.toInt()
                            mediaPlayer = MediaPlayer()
                            mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
                            //play?.isClickable=false
                            try {
                                mediaPlayer!!.setDataSource(
                                    this@PojazdyLayout,
                                    piosenka.songUrl.toUri()
                                )
                                mediaPlayer!!.prepare()
                                mediaPlayer!!.start()

                            } catch (e: IOException) {
                                Toast.makeText(
                                    this@PojazdyLayout,
                                    "Error found is $e",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        } else {
                            Log.d(ContentValues.TAG, "No such document")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d(ContentValues.TAG, "get failed with ", exception)
                    }
            }
        })



        for (i in nazwy.indices) {
            nazwy[i].setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    mediaPlayer!!.pause()
                    var zmienna = indeksy.get(i)

                    if (id == zmienna) {
                        val k = ImageView(getApplicationContext())
                        k.setImageResource(R.drawable.dobrze)
                        val toast = Toast(getApplicationContext())
                        toast.setDuration(Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.setView(k)
                        toast.show()

                        punkty += 1
                        licznik+=1
                        czyLosowac = true

                        if (licznik == 9) {
                            openBrawo()
                        }
                    } else {
                        val k = ImageView(getApplicationContext())
                        k.setImageResource(R.drawable.zle)
                        val toast = Toast(getApplicationContext())
                        toast.setDuration(Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER, 0, 0)
                        toast.setView(k)
                        toast.show()
                        czyLosowac = true
                        licznik+=1
                    }
                }
            })
        }


    }

    private fun openLayoutMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun openBrawo() {
        val pkt = punkty.toString()
        val intent = Intent(this, BrawoActivity::class.java).apply {
            putExtra("zmienna",pkt)
        }
        startActivity(intent)
    }}