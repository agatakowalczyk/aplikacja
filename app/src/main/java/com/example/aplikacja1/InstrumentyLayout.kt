package com.example.aplikacja1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class InstrumentyLayout : AppCompatActivity() {

    private var instrumenty: TextView? = null;
    private var pytanie: TextView? = null;
    private var marakasy: Button? =null;
    private var harmonijka: Button? =null;
    private var harfa: Button? =null;
    private var trojkat: Button? =null;
    private var beben: Button? =null;
    private var fortepian: Button? =null;
    private var skrzypce: Button? =null;
    private var flet: Button? =null;
    private var gitara: Button? =null;

    private var play: Button? =null;
    private var wstecz: Button? =null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instrumenty_layout)


        instrumenty =findViewById(R.id.instrumenty)
        pytanie = findViewById(R.id.instrumenty_tekst)

        marakasy = findViewById(R.id.marakasy)
        harmonijka = findViewById(R.id.harmonijka)
        harfa = findViewById(R.id.harfa)
        trojkat = findViewById(R.id.trojkat)
        beben = findViewById(R.id.beben)
        fortepian = findViewById(R.id.fortepian)
        skrzypce = findViewById(R.id.skrzypce)
        flet = findViewById(R.id.flet)
        gitara = findViewById(R.id.gitara)

        play = findViewById(R.id.odtwarzaj3)
        wstecz = findViewById(R.id.powrot3)
    }
}