package com.tasnim.chowdhury.eee.ui.ie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.databinding.FragmentIncomeExpenseUpdateBinding
import com.tasnim.chowdhury.eee.model.data.IncomeExpense

class IncomeExpenseUpdateFragment : Fragment() {

    private lateinit var binding: FragmentIncomeExpenseUpdateBinding

    private val args by navArgs<IncomeExpenseUpdateFragmentArgs>()
    private lateinit var viewModel: IncomeExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentIncomeExpenseUpdateBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpData()

        viewModel = ViewModelProvider(this)[IncomeExpenseViewModel::class.java]

        binding.updateButton.setOnClickListener {
            updateIncomeExpense()
        }
    }

    private fun setUpData() {
        binding.updateTitle.setText(args.currentItem.iETitle)
        binding.updateType.setText(args.currentItem.iEType)
        binding.updateNote.setText(args.currentItem.iENote)
        binding.updateCategory.setText(args.currentItem.iECategory)
        binding.updatePaymentMethod.setText(args.currentItem.paymentMethod)
        binding.updateTime.setText(args.currentItem.iETimeStamp)
        binding.updateDate.setText(args.currentItem.iEDate)
        binding.updateAmount.setText(args.currentItem.iEAmount.toString())
    }

    private fun updateIncomeExpense(){
        val title = binding.updateTitle.text.toString()
        val type = binding.updateType.text.toString()
        val note = binding.updateNote.text.toString()
        val time = binding.updateTime.text.toString()
        val date = binding.updateDate.text.toString()
        val amount = binding.updateAmount.text.toString()
        val category = binding.updateCategory.text.toString()
        val paymentMethod = binding.updatePaymentMethod.text.toString()

        if (binding.updateTitle.text.isNullOrEmpty() || binding.updateType.text.isNullOrEmpty() || binding.updateAmount.text.isNullOrEmpty()){
            Toast.makeText(requireContext(), "Update Failed! Please fill all the required field.", Toast.LENGTH_SHORT).show()
            binding.updateTitle.requestFocus()
        }else{
            val updateIncomeExpense = IncomeExpense(args.currentItem.iEId, title, type, note, time, amount.toLong(), category, date, paymentMethod)

            viewModel.updateIncomeExpense(updateIncomeExpense)
            findNavController().popBackStack()

            Toast.makeText(requireContext(), "Updated Income/Expense.", Toast.LENGTH_SHORT).show()
        }
    }

}