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
    val mFireStore = FirebaseFirestore.getInstance()

    //guziki do obslugiwania
    private var odtworz: AppCompatImageButton? =null;
    private var wstecz: AppCompatImageButton? =null;
    //val storage:FirebaseStorage = FirebaseStorage.getInstance("gs://baza-dzwiekow.appspot.com")

    private var mMediaplayer: MediaPlayer? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoo_layout)

        nazwaTrybu =findViewById(R.id.nazwaPojazdy)
        wyswietlanyTekst = findViewById(R.id.JakiPojazd_tekst)
        wykrzyknik = findViewById(R.id.JakiPojazd_tekst)

        odtworz =findViewById(R.id.odtwarzaj)
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
                    getResourceID(
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

        odtworz?.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?){
                otworzZoo()
            }
        })
    }

    private fun openLayoutMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    fun otworzZoo() {
        var docRef = mFireStore.collection(Nazwy.INST).document("song1")
        val mediaID=""
        docRef.get()
            .addOnSuccessListener (){ document ->
                if (document != null) {
                    Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}")

                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }

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