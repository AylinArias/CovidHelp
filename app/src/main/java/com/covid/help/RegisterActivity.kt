package com.covid.help

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        updateTeclado()

        btn_crearCuenta.setOnClickListener {
            val email = email_editText.text.toString()
            val contraseña = contraseña_editText.text.toString()
            val nombre = nombre_editText.text.toString()

            auth.createUserWithEmailAndPassword(email, contraseña)
                .addOnSuccessListener {
                    val profile = UserProfileChangeRequest.Builder()
                        .setDisplayName(nombre)
                        .build()

                    it.user!!.updateProfile(profile)
                        .addOnSuccessListener {
                            AlertDialog.Builder(this).apply {
                                setTitle("Cuenta creada")
                                setMessage("Tu cuenta se creo correctamente")
                                setPositiveButton("Aceptar"){dialogInterface, i ->
                                    finish()
                                }
                        }.show()
                }
                .addOnFailureListener {
                    Utils.showError(this, it.message.toString())
                }
                        .addOnFailureListener {
                            Utils.showError(this, it.message.toString())
                        }
                }
        }
    }

    private fun updateTeclado(){
        consL.setOnClickListener{
            ocultarTeclado(consL)
        }
    }

    private fun ocultarTeclado(view: View){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)
    }
}


