package com.covid.help

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_foro.*
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class ForoActivity : AppCompatActivity() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foro)


        db.collection("posts").addSnapshotListener { value, error ->
            val posts = value!!.toObjects(Post::class.java)

            posts.forEachIndexed { index, post ->
                post.uid = value.documents[index].id
            }

            rv.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@ForoActivity)
                adapter = PostAdapter(this@ForoActivity, posts)
            }
        }

        add.setOnClickListener {
            val intentPost = Intent(this, CreatePostActivity::class.java)
            startActivity(intentPost)
        }

    }

}




