package com.covid.help

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        updateTeclado()

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordlEditText.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    val intent =  Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener {
                    Utils.showError(this, it.message.toString())
                }

            }
        crearCuenta_textV.setOnClickListener{
            val intent =  Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun updateTeclado(){
        cL.setOnClickListener{
            ocultarTeclado(cL)
        }
    }

    private fun ocultarTeclado(view: View){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)
    }
}