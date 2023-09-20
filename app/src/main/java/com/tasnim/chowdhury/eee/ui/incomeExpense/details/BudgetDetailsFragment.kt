package com.tasnim.chowdhury.eee.ui.incomeExpense.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.databinding.FragmentBudgetDetailsBinding

class BudgetDetailsFragment : Fragment() {

    private lateinit var binding: FragmentBudgetDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return FragmentBudgetDetailsBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setupClicks()
        handleBackPressed()

    }

    private fun initData() {

    }

    private fun setupClicks() {

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