package com.example.aplikacja1

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.net.toUri
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.io.IOException
import java.util.*
import kotlin.random.Random


class ZooLayout : AppCompatActivity() {

    private var nazwaTrybu: TextView? = null;
    private var wyswietlanyTekst: TextView? = null;
    private var wykrzyknik: TextView? = null;

    //guziki do obslugiwania
    private var play: AppCompatImageButton? =null;
    private var wstecz: AppCompatImageButton? =null;

    var losowe: ArrayList<Int> = ArrayList()
    val indeksy: ArrayList<Int> = ArrayList()
    var piosenki: ArrayList<String> = ArrayList()

    val obj = Funkcje()
    var czyLosowac = true
    var punkty = 0
    var licznik = 0
    var id = 0
    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoo_layout)

        nazwaTrybu =findViewById(R.id.nazwaPojazdy)
        wyswietlanyTekst = findViewById(R.id.JakiPojazd_tekst)
        wykrzyknik = findViewById(R.id.JakiPojazd_tekst)

        play =findViewById(R.id.odtwarzaj)
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
                var dok = obj.losuj(czyLosowac, losowe, Nazwy.ZOO)
                if (!piosenki.contains(dok)) {
                    piosenki.add(dok)
                }
                czyLosowac = false

                val mFireStore = FirebaseFirestore.getInstance()
                var docRef = mFireStore.collection(Nazwy.ZOO).document(dok)
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
                                    this@ZooLayout,
                                    piosenka.songUrl.toUri()
                                )
                                mediaPlayer!!.prepare()
                                mediaPlayer!!.start()

                            } catch (e: IOException) {
                                Toast.makeText(
                                    this@ZooLayout,
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

                    if(piosenki.count()!=0){
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
                                toast.cancel()
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
                    else{
                        Log.d(ContentValues.TAG, "Brak muzyki")
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
        finish()
    }


}