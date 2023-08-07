package com.tasnim.chowdhury.eee.ui.incomeExpense.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.databinding.FragmentRecordDetailsBinding
import com.tasnim.chowdhury.eee.ui.MainFragmentDirections

class RecordDetailsFragment : Fragment() {

    private lateinit var binding: FragmentRecordDetailsBinding
    private val args by navArgs<RecordDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentRecordDetailsBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClicks()

        val callBack = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callBack)
    }

    private fun setupClicks() {
        val data = args.recordDetails

        if (data != null){
            binding.toolBarBackIcon.setOnClickListener {
                findNavController().navigateUp()
            }

            binding.toolBarEditIcon.setOnClickListener {
                val action = RecordDetailsFragmentDirections.actionRecordDetailsFragmentToIncomeExpenseUpdateFragment(data)
                findNavController().navigate(action)
            }

            binding.recordAmount.text = "BDT ${data.iEAmount.toString()}"
            binding.recordTitle.text = data.iETitle
        }
    }

}