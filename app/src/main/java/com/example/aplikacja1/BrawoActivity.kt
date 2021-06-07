package com.example.aplikacja1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton

class BrawoActivity : AppCompatActivity() {

    private var brawo: TextView? = null;

    private var balonczerwony: AppCompatImageButton? =null;
    private var balonzielony: AppCompatImageButton? =null;
    private var balonzolty: AppCompatImageButton? =null;
    private var balonzolty2: AppCompatImageButton? =null;
    private var balonserduszka: AppCompatImageButton? =null;

    private var wstecz: AppCompatImageButton? =null;

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

        wstecz?.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?){
                openLayoutMain()
            }
        })
        balonczerwony?.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?){
                openLayoutMain()
            }
        })
        balonzielony?.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?){
                openLayoutMain()
            }
        })
        balonzolty?.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?){
                openLayoutMain()
            }
        })
        balonzolty2?.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?){
                openLayoutMain()
            }
        })
        balonserduszka?.setOnClickListener(object: View.OnClickListener {
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