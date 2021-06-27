package com.example.aplikacja1

import android.app.Activity
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
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

open class Funkcje {

        fun playFromFirebase(kolekcja:String,nazwa: String, activity: Activity):Int {
            var mediaPlayer: MediaPlayer? = null
            val mFireStore = FirebaseFirestore.getInstance()
            var docRef = mFireStore.collection(kolekcja).document(nazwa)
            var piosenka = Song()
            var id = 0
            docRef.get()
                .addOnSuccessListener() { document ->
                    if (document != null) {
                        Log.d(ContentValues.TAG, "DocumentSnapshot data: ${document.data}")
                        piosenka = document.toObject(Song::class.java)!!
                        id = piosenka.mediaId.toInt()
                        Log.d(ContentValues.TAG, "DocumentSnapshot data: $id")
                        mediaPlayer = MediaPlayer()
                        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)

                        try {
                            println("Dupa")
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
            println(id)
            return id
        }

    fun losuj(czylosowac: Boolean, lista: ArrayList<Int>, layout: String): String {
        var nazwaDokumentu = ""
        var indeks = 0
        var rozmiar = lista.count()
        if (lista.count() < 9) {
            if (czylosowac) {
                while (lista.count() == rozmiar) {
                    indeks = Random.nextInt(1, 10)
                    if (!lista.contains(indeks)) {
                        nazwaDokumentu = layout + indeks
                        lista.add(indeks);
                    }
                }
            } else {
                indeks = lista.get(lista.lastIndex)
                nazwaDokumentu = layout + indeks
            }

        }
        return nazwaDokumentu
    }

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