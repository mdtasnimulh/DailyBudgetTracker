package com.tasnim.chowdhury.eee.ui.incomeExpense

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.databinding.FragmentCalculatorDialogBinding

class CalculatorDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentCalculatorDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentCalculatorDialogBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        val contentView = requireActivity().layoutInflater.inflate(
            R.layout.fragment_calculator_dialog,
            null
        )

        dialog.setContentView(contentView)

        val width = (resources.displayMetrics.widthPixels * 0.97).toInt()
        dialog.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClicks()
    }

    private fun setupClicks() {
        binding.buttonOk.setOnClickListener {
            dialog?.dismiss()
            Toast.makeText(requireContext(), "Calculator Closed.", Toast.LENGTH_SHORT).show()
        }
    }

    companion object{
        const val TAG = "InsertAmountDialog"
    }

}