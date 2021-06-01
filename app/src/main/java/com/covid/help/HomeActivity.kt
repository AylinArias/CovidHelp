package com.covid.help

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class HomeActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setUp()

    }

    private fun setUp() {


        btnNoticias.setOnClickListener {
            val newsIntent = Intent(this, NoticiasActivity::class.java)
            startActivity(newsIntent)
        }

        btnForo.setOnClickListener {
            val foroIntent = Intent(this, ForoActivity::class.java)
            startActivity(foroIntent)
        }

        btn_acercaDe.setOnClickListener {
            val acercaIntent = Intent(this, AboutActivity::class.java)
            startActivity(acercaIntent)
        }

        btn_salir.setOnClickListener {
            val intentSalir = Intent(this, SplashActivity::class.java)
            startActivity(intentSalir)
            finish()
        }

    }

}