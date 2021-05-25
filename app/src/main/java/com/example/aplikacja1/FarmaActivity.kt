package com.example.aplikacja1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class FarmaActivity : AppCompatActivity() {

    private var farma: TextView? = null;
    private var pytanie: TextView? = null;

    private var ges: Button? =null;
    private var kon: Button? =null;
    private var kaczka: Button? =null;
    private var kogut: Button? =null;
    private var kot: Button? =null;
    private var krowa: Button? =null;
    private var swinia: Button? =null;
    private var pies: Button? =null;
    private var owca: Button? =null;

    private var play: Button? =null;
    private var wstecz: Button? =null;

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

    }
}