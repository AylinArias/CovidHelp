package com.covid.help

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var handler: Handler
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_main)

        handler = Handler()
        handler.postDelayed({

            // Delay and Start Activity
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        } , 3000) // here we're delaying to startActivity after 3seconds

    }

}