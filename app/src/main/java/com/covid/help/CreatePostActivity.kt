package com.covid.help

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_create_post.*
import java.util.*

class CreatePostActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        updateTeclado()

        btn_publicar.setOnClickListener {
            val publicarIntent = Intent(this, ForoActivity::class.java)
            startActivity(publicarIntent)

            val postString = edText_post.text.toString()
            val date = Date()
            val userName = auth.currentUser!!.displayName

            val post = Post(postString, date, userName)

            db.collection("posts").add(post)
                .addOnSuccessListener {
                    finish()
                }
                .addOnFailureListener {
                    Utils.showError(this, it.message.toString())
                }
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