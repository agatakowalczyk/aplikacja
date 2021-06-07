package com.example.aplikacja1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton

class InstrumentyLayout : AppCompatActivity() {

    private var instrumenty: TextView? = null;
    private var pytanie: TextView? = null;
    private var marakasy: AppCompatImageButton? =null;
    private var harmonijka: AppCompatImageButton? =null;
    private var harfa: AppCompatImageButton? =null;
    private var trojkat: AppCompatImageButton? =null;
    private var beben: AppCompatImageButton? =null;
    private var fortepian: AppCompatImageButton? =null;
    private var skrzypce: AppCompatImageButton? =null;
    private var flet: AppCompatImageButton? =null;
    private var gitara: AppCompatImageButton? =null;

    private var play: AppCompatImageButton? =null;
    private var wstecz: AppCompatImageButton? =null;

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