package com.simple.simplecalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var screenView:TextView
    lateinit var sendRes:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callViwes()
        val sendResult :Intent =Intent().apply {
            action =Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,screenView.text)
            type="text/plain"
        }
        sendRes.setOnClickListener {
            startActivity(sendResult)

        }
    }
    fun callViwes(){
        screenView=findViewById(R.id.textView2)
        sendRes=findViewById(R.id.imageButton)
    }
}