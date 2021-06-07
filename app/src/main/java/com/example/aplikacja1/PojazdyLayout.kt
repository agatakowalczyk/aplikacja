package com.example.aplikacja1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton

class PojazdyLayout : AppCompatActivity() {

    private var nazwaTrybu: TextView? = null;
    private var wykrzyknik: TextView? = null;
    private var wyswietlanyTekst: TextView? = null;
    //guziki ze zwierzetami
    private var rower: AppCompatImageButton? =null;
    private var statek: AppCompatImageButton? =null;
    private var pociag: AppCompatImageButton? =null;
    private var truck: AppCompatImageButton? =null;
    private var samochod: AppCompatImageButton? =null;
    private var samolot: AppCompatImageButton? =null;
    private var policja: AppCompatImageButton? =null;
    private var helikopter: AppCompatImageButton? =null;
    private var motor: AppCompatImageButton? =null;
    //guziki do obslugiwania apki
    private var odtworz: AppCompatImageButton? =null;
    private var wstecz: AppCompatImageButton? =null;


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