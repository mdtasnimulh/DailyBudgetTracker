package com.tasnim.chowdhury.eee.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tasnim.chowdhury.eee.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}