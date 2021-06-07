package com.example.aplikacja1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton

class FarmaActivity : AppCompatActivity() {

    private var farma: TextView? = null;
    private var pytanie: TextView? = null;

    private var ges: AppCompatImageButton? =null;
    private var kon: AppCompatImageButton? =null;
    private var kaczka: AppCompatImageButton? =null;
    private var kogut: AppCompatImageButton? =null;
    private var kot: AppCompatImageButton? =null;
    private var krowa: AppCompatImageButton? =null;
    private var swinia: AppCompatImageButton? =null;
    private var pies: AppCompatImageButton? =null;
    private var owca: AppCompatImageButton? =null;

    private var play: AppCompatImageButton? =null;
    private var wstecz: AppCompatImageButton? =null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farma)

        farma =findViewById(R.id.farma)
        pytanie = findViewById(R.id.farma_tekst)

        ges = findViewById(R.id.ges)
        kon = findViewById(R.id.kon)
        kaczka = findViewById(R.id.kaczka)
        kogut = findViewById(R.id.kogut)
        kot = findViewById(R.id.kot)
        krowa = findViewById(R.id.krowa)
        swinia = findViewById(R.id.swinia)
        pies = findViewById(R.id.pies)
        owca = findViewById(R.id.owca)

        play = findViewById(R.id.odtwarzaj4)
        wstecz = findViewById(R.id.powrot4)

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
}