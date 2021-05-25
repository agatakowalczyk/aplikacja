package com.example.aplikacja1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class PojazdyLayout : AppCompatActivity() {

    private var nazwaTrybu: TextView? = null;
    private var wykrzyknik: TextView? = null;
    private var wyswietlanyTekst: TextView? = null;
    //guziki ze zwierzetami
    private var rower: Button? =null;
    private var statek: Button? =null;
    private var pociag: Button? =null;
    private var truck: Button? =null;
    private var samochod: Button? =null;
    private var samolot: Button? =null;
    private var policja: Button? =null;
    private var helikopter: Button? =null;
    private var motor: Button? =null;
    //guziki do obslugiwania apki
    private var odtworz: Button? =null;
    private var wstecz: Button? =null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pojazdy_layout)

        nazwaTrybu =findViewById(R.id.nazwaPojazdy)
        wyswietlanyTekst = findViewById(R.id.JakiPojazd_tekst)
        wykrzyknik = findViewById(R.id.JakiPojazd_tekst)

        rower = findViewById(R.id.rower)
        statek = findViewById(R.id.statek)
        pociag = findViewById(R.id.pociag)
        truck = findViewById(R.id.truck)
        samochod = findViewById(R.id.samochod)
        samolot = findViewById(R.id.samolot)
        policja = findViewById(R.id.policja)
        helikopter = findViewById(R.id.helikopter)
        motor = findViewById(R.id.motor)

        odtworz =findViewById(R.id.odtwarzaj2)
        wstecz = findViewById(R.id.powrot2)
    }
}