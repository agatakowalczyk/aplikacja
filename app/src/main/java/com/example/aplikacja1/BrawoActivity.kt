package com.example.aplikacja1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class BrawoActivity : AppCompatActivity() {

    private var brawo: TextView? = null;

    private var balonczerwony: Button? =null;
    private var balonzielony: Button? =null;
    private var balonzolty: Button? =null;
    private var balonzolty2: Button? =null;
    private var balonserduszka: Button? =null;

    private var wstecz: Button? =null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brawo)


        brawo = findViewById(R.id.brawo)

        balonczerwony = findViewById(R.id.balon_czerwony)
        balonzielony = findViewById(R.id.balon_zielony)
        balonzolty = findViewById(R.id.balon_zolty)
        balonzolty2 = findViewById(R.id.balon_zolty2)
        balonserduszka = findViewById(R.id.balon_serduszka)

        wstecz = findViewById(R.id.powrot5)
    }
}