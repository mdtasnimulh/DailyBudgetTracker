package com.tasnim.chowdhury.eee.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.findNavController
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val navHostFragment = supportFragmentManager.findFragmentById(R.id.loginNavHostFragment) as NavHostFragment
        //val navController = navHostFragment.findNavController()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.loginNavHostFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}