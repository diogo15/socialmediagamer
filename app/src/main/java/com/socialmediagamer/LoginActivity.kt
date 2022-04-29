package com.socialmediagamer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth
import com.socialmediagamer.databinding.ActivityLoginBinding
import com.socialmediagamer.model.Perfil
import com.socialmediagamer.ui.dashboard.PerfilViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    private lateinit var perfilViewModel: PerfilViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        setContentView(R.layout.activity_main)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener { hacelogin() }
        binding.btnRegister.setOnClickListener { haceRegister() }

        perfilViewModel = ViewModelProvider(this)[PerfilViewModel::class.java]
    }

    fun hacelogin(){

        val user = binding.txtUser.text.toString()
        val clave = binding.txtPass.text.toString()

        auth.signInWithEmailAndPassword(user,clave)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }

    }

    private fun haceRegister(){
        val email = binding.txtUser.text.toString()
        val clave = binding.txtPass.text.toString()

        auth.createUserWithEmailAndPassword(email,clave)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    Log.d("Auth", "Usuario creado")
                    val user = auth.currentUser
                    val perfil = Perfil("", "", "", "", "","")
                    perfilViewModel.addPerfil(perfil)
                    actualiza(user)
                }
            }.addOnFailureListener(this){task->
                Log.d("Auth", task.message.toString())
                Log.d("Auth", "Falllloooo")
            }

    }

    private fun actualiza(user: FirebaseUser? ){
        if (user != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}