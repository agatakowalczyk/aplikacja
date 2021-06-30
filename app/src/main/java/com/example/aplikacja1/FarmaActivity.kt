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
import android.widget.*
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.net.toUri
import com.google.firebase.firestore.FirebaseFirestore
import java.io.IOException
import java.lang.IllegalArgumentException
import java.util.ArrayList
import kotlin.random.Random

class FarmaActivity : AppCompatActivity() {

    private var farma: TextView? = null;
    private var pytanie: TextView? = null;

    private var play: AppCompatImageButton? =null;
    private var wstecz: AppCompatImageButton? =null;


    private var losowe: ArrayList<Int> = ArrayList()
    private val indeksy: ArrayList<Int> = ArrayList()
    private var piosenki: ArrayList<String> = ArrayList()

    private val obj = Funkcje()
    private var czyLosowac = true
    private var punkty = 0
    private var licznik = 0
    private var id = 0
    private var mediaPlayer: MediaPlayer? = null

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
                if (!piosenki.contains(dok)) {
                    piosenki.add(dok)
                }
                czyLosowac = false

                val mFireStore = FirebaseFirestore.getInstance()
                var docRef = mFireStore.collection(Nazwy.FAR1).document(dok)
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
                                    this@FarmaActivity,
                                    piosenka.songUrl.toUri()
                                )
                                mediaPlayer!!.prepare()
                                mediaPlayer!!.start()

                            } catch (e: IOException) {
                                Toast.makeText(
                                    this@FarmaActivity,
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

    private fun openLayoutMain(){
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