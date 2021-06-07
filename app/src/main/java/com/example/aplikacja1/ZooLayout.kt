package com.example.aplikacja1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.content.Intent
import androidx.appcompat.widget.AppCompatImageButton

class ZooLayout : AppCompatActivity() {

    private var nazwaTrybu: TextView? = null;
    private var wyswietlanyTekst: TextView? = null;
    private var wykrzyknik: TextView? = null;
    //guziki ze zwierzetami
    private var delfin: AppCompatImageButton? =null;
    private var slon: AppCompatImageButton? =null;
    private var papuga: AppCompatImageButton? =null;
    private var waz: AppCompatImageButton? =null;
    private var foka: AppCompatImageButton? =null;
    private var lew: AppCompatImageButton? =null;
    private var sowa: AppCompatImageButton? =null;
    private var malpa: AppCompatImageButton? =null;
    private var wilk: AppCompatImageButton? =null;
    //guziki do obslugiwania apki
    private var odtworz: AppCompatImageButton? =null;
    private var wstecz: AppCompatImageButton? =null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoo_layout)

        nazwaTrybu =findViewById(R.id.nazwaPojazdy)
        wyswietlanyTekst = findViewById(R.id.JakiPojazd_tekst)
        wykrzyknik = findViewById(R.id.JakiPojazd_tekst)

        delfin = findViewById(R.id.delfin)
        slon = findViewById(R.id.slon)
        papuga = findViewById(R.id.papuga)
        waz = findViewById(R.id.wunsz)
        foka = findViewById(R.id.foka)
        lew = findViewById(R.id.lew)
        sowa = findViewById(R.id.sowa)
        malpa = findViewById(R.id.monkey)
        wilk = findViewById(R.id.wilk)

        odtworz =findViewById(R.id.odtwarzaj)
        wstecz = findViewById(R.id.powrot)

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