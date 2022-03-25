package com.socialmediagamer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.socialmediagamer.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun hacelogin(){

        val user = binding.txtUser.text.toString()
        val clave = binding.txtPass.text.toString()

        auth.signInWithEmailAndPassword(user,clave)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
            }

        //bypass login
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

    }

}