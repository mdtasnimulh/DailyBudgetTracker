package com.tasnim.chowdhury.eee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.tasnim.chowdhury.eee.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        setupClicks()
    }

    private fun setupClicks() {
        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.tvRedirectSignUp.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
            finish()
        }
    }

    private fun login() {
        val email = binding.etEmailAddress.text.toString()
        val pass = binding.etPassword.text.toString()

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
            }
        }
    }
}