package com.example.aplikacja1

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class Dupa {

    val mFireStore = FirebaseFirestore.getInstance()
    fun otworzZoo() {
        var docRef = mFireStore.collection(Nazwy.INST).document("song1")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

    }
}