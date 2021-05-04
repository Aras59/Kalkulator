package com.example.kalkulator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
    }

    fun simpleCalculatorStart(view: View){
        val intent = Intent(this,Simple::class.java).apply{}
        startActivity(intent)
    }

    fun AdvanceCalculatorStart(view: View){
        val intent = Intent(this,Advance::class.java).apply{}
        startActivity(intent)
    }

    fun aboutActivityStart(view: View){
        val intent = Intent(this,About::class.java).apply{}
        startActivity(intent)
    }

    fun exitApp(view: View){
        finish();
        System.exit(0);
    }



}