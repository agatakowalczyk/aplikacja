package com.example.aplikacja1

import android.app.Activity
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.media.AudioManager
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import androidx.core.net.toUri
import com.example.aplikacja1.Nazwy.INST
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.io.IOException
import kotlin.random.Random

open class Funkcje{
companion object{
   fun playFromFirebase(nazwa:String,activity:Activity) {
       var mediaPlayer: MediaPlayer? = null
       val mFireStore = FirebaseFirestore.getInstance()
       var docRef = mFireStore.collection(Nazwy.INST).document(nazwa)
       var piosenka = Song()
       docRef.get()
           .addOnSuccessListener() { document ->
               if (document != null) {
                   Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}")
                   piosenka = document.toObject(Song::class.java)!!
                   mediaPlayer = MediaPlayer()
                   mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
                   try {
                       mediaPlayer!!.setDataSource(activity, piosenka.songUrl.toUri())
                       mediaPlayer!!.prepare()
                       mediaPlayer!!.start()
                       Toast.makeText(activity, "Audio started playing..", Toast.LENGTH_SHORT)
                           .show()
                   } catch (e: IOException) {
                       Toast.makeText(activity, "Error found is $e", Toast.LENGTH_SHORT).show()
                   }

               } else {
                   Log.d(ContentValues.TAG, "No such document")
               }
           }
           .addOnFailureListener { exception ->
               Log.d(ContentValues.TAG, "get failed with ", exception)
           }
   }
}
fun losuj (czylosowac:Boolean, lista:ArrayList<Int>,layout:String):String{
    var nazwaDokumentu = ""
    var indeks = 0
    var rozmiar = lista.count()
    if (lista.count() < 9) {
        if(czylosowac){
            while(lista.count()==rozmiar){
            indeks = Random.nextInt(1, 5)
            if (!lista.contains(indeks)) {
                nazwaDokumentu = layout+indeks
                lista.add(indeks);
            }
            }
        }
        else
        {
            indeks = lista.get(lista.lastIndex)
            nazwaDokumentu = layout+indeks
        }

    }
    return nazwaDokumentu
}

}