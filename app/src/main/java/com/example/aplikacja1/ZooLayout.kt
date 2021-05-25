package com.example.aplikacja1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.content.Intent

class ZooLayout : AppCompatActivity() {

    private var nazwaTrybu: TextView? = null;
    private var wyswietlanyTekst: TextView? = null;
    private var wykrzyknik: TextView? = null;
    //guziki ze zwierzetami
    private var delfin: Button? =null;
    private var slon: Button? =null;
    private var papuga: Button? =null;
    private var waz: Button? =null;
    private var foka: Button? =null;
    private var lew: Button? =null;
    private var sowa: Button? =null;
    private var malpa: Button? =null;
    private var wilk: Button? =null;
    //guziki do obslugiwania apki
    private var odtworz: Button? =null;
    private var wstecz: Button? =null;


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
    }

}