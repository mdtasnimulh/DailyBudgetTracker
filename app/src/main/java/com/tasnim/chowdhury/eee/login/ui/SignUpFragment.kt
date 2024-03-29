package com.tasnim.chowdhury.eee.login.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClicks()
    }

    override fun onResume() {
        super.onResume()

        handleBackPressed()
    }

    private fun setUpClicks() {
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.loginBtn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }

    private fun handleBackPressed(){
        val callBack = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callBack)
    }

}