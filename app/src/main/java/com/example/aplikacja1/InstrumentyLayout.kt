package com.example.aplikacja1

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.net.toUri
import com.google.firebase.firestore.FirebaseFirestore
import java.io.IOException
import java.util.ArrayList
import kotlin.random.Random

class InstrumentyLayout : AppCompatActivity() {

    private var instrumenty: TextView? = null;
    private var pytanie: TextView? = null;

    private var play: AppCompatImageButton? =null;
    private var wstecz: AppCompatImageButton? =null;


//    private var x :String? = null;

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instrumenty_layout)



//
//            play?.setOnClickListener {
//                openLayoutMain()
//                mediaPlayer = MediaPlayer()
//                mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
//                try {
//                    mediaPlayer!!.setDataSource(applicationContext, otworzBaze())
//                    mediaPlayer!!.prepare()
//                    mediaPlayer!!.start()
//                    Toast.makeText(this, "Audio started playing..", Toast.LENGTH_SHORT).show()
//                } catch (e: IOException) {
//                    Toast.makeText(this, "Error found is $e", Toast.LENGTH_SHORT).show()
//                }
//
//            }




        instrumenty =findViewById(R.id.instrumenty)
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
        val indeksy: ArrayList<Int> = ArrayList()
        val size = 10


        while(indeksy.count()<9) {
            var losuj = Random.nextInt(1,10)
            if (!indeksy.contains(losuj)) {
                indeksy.add(losuj);
            }
        }

        nazwy.forEachIndexed{index, it ->
            var str = "inst" + indeksy.get(index).toString()
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

    val mFireStore = FirebaseFirestore.getInstance()

//    fun otworzBaze(): String {
//        //val mFireStore = FirebaseFirestore.getInstance()
//        var docRef = mFireStore.collection(Nazwy.INST).document("song1")
//        var mediaID=""
//        docRef.get()
//            .addOnSuccessListener (){ document ->
//                if (document != null) {
//                    Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}")
//                    val piosenka= document.toObject(Song::class.java)!!
////                    val urlsong = document.toObject(Song::class.java)!!
////                     x = urlsong.songUrl
//                    mediaID = piosenka.mediaID
//                } else {
//                    Log.d(ContentValues.TAG, "No such document")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d(ContentValues.TAG, "get failed with ", exception)
//            }
//        return mediaID
//    }


    fun otworzBaze():Uri{
        //val mFireStore = FirebaseFirestore.getInstance()
        var docRef = mFireStore.collection(Nazwy.INST).document("song1")
//        var mediaID = ""
        var piosenkaUrl =""
        docRef.get()
            .addOnSuccessListener (){ document ->
                if (document != null) {
                    Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}")
                    val piosenka= document.toObject(Song::class.java)!!
//                    mediaID = piosenka.mediaID
//                    piosenkaUrl = piosenka.songUrl.toString()

//
//                    when(activity){
//                        is InstrumentyLayout -> { // tutaj wpisujemy  aktywność
//                            activity.playAudio(piosenka)
//                        }
//                    }
                } else {
                    Log.d(ContentValues.TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(ContentValues.TAG, "get failed with ", exception)
            }

    return piosenkaUrl.toUri()
    }



    var mediaPlayer: MediaPlayer? = null
//
//    private fun playAudio( x: Song) {
//
//        // initializing media player
//        mediaPlayer = MediaPlayer()
//        val url = "https://firebasestorage.googleapis.com/v0/b/baza-dzwiekow.appspot.com/o/instrumenty%2Fbeben.mp3?alt=media&token=b8969f72-3843-471b-bf85-70e6de866cad"
//
//        // below line is use to set the audio stream type for our media player.
//        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
//        try {
//            // below line is use to set our
//            // url to our media player.
////            mediaPlayer!!.setDataSource(url)
//            mediaPlayer!!.setDataSource(applicationContext, x.songUrl)
//
//            // below line is use to prepare
//            // and start our media player.
//            mediaPlayer!!.prepare()
//            mediaPlayer!!.start()
//
//            // below line is use to display a toast message.
//            Toast.makeText(this, "Audio started playing..", Toast.LENGTH_SHORT).show()
//        } catch (e: IOException) {
//            // this line of code is use to handle error while playing our audio file.
//            Toast.makeText(this, "Error found is $e", Toast.LENGTH_SHORT).show()
//        }
//
//    }

    fun playAudio(view: View) {

        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
        try {
            mediaPlayer!!.setDataSource(applicationContext,otworzBaze())
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()
            Toast.makeText(this, "Audio started playing..", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            Toast.makeText(this, "Error found is $e", Toast.LENGTH_SHORT).show()
        }
    }


}


