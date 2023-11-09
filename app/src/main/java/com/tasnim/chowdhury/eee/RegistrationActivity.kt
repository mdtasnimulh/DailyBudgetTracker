package com.tasnim.chowdhury.eee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.tasnim.chowdhury.eee.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    //private lateinit var auth: FirebaseAuth

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //auth = Firebase.auth

        setupClicks()
    }

    private fun setupClicks() {
        binding.btnSSigned.setOnClickListener {
            signUpUser()
        }

        binding.tvRedirectLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signUpUser() {
        val fullName = binding.etName.text.toString()
        val email = binding.etSEmailAddress.text.toString()
        val pass = binding.etSPassword.text.toString()
        val confirmPass = binding.etSConfPassword.text.toString()
        val gender = binding.etGender.text.toString()

        if (email.isBlank() || pass.isBlank() || confirmPass.isBlank() || gender.isBlank() || fullName.isBlank()){
            Toast.makeText(this, "Fields can't be blank!.", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confirmPass){
            Toast.makeText(this, "Password and Confirm Password not matched.", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val user = hashMapOf(
            "name" to fullName,
            "email" to email,
            "pass" to pass,
            "gender" to gender,
        )
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        if (userId != null) {
            db.collection("users")
                .add(user)
                .addOnSuccessListener {
                    Toast.makeText(this, "Successfully registered $fullName", Toast.LENGTH_SHORT).show()
                    binding.etName.text.clear()
                    binding.etSEmailAddress.text.clear()
                    binding.etSPassword.text.clear()
                    binding.etSConfPassword.text.clear()
                    binding.etGender.text.clear()
                }
                .addOnFailureListener{
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
                }
        }

        /*auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this){
            if (it.isSuccessful){
                Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                val user = auth.currentUser
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }*/
    }
}