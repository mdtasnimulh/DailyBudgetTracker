package com.tasnim.chowdhury.eee

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tasnim.chowdhury.eee.databinding.FragmentInsertIEBinding
import com.tasnim.chowdhury.eee.model.data.IncomeExpense
import java.time.temporal.TemporalAmount

class InsertIEFragment : Fragment() {

    private lateinit var binding: FragmentInsertIEBinding
    private lateinit var viewModel: IncomeExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentInsertIEBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[IncomeExpenseViewModel::class.java]

        binding.insertButton.setOnClickListener {
            insertIncomeExpense()
        }
    }

    private fun insertIncomeExpense() {
        val title = binding.title.text.toString()
        val type = binding.type.text.toString()
        val note = binding.note.text.toString()
        val time = binding.time.text.toString()
        val date = binding.date.text.toString()
        val amount = binding.amount.text.toString()
        val category = binding.category.text.toString()
        val paymentMethod = binding.paymentMethod.text.toString()

        if (inputCheck(title, type, time, date, amount.toLong())){
            val incomeExpense = IncomeExpense(title, type, note, time, amount.toLong(), category, date, paymentMethod)

            viewModel.insertIncomeExpense(incomeExpense)
            findNavController().navigate(R.id.action_insertIEFragment_to_mainFragment)

            Toast.makeText(requireContext(), "Successfully Added Income/Expense.", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(), "Please fill all the required field.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(title: String, type: String, time: String, date: String, amount: Long): Boolean{
        return !(TextUtils.isEmpty(title) &&
                TextUtils.isEmpty(type) &&
                TextUtils.isEmpty(time) &&
                TextUtils.isEmpty(date) &&
                TextUtils.isEmpty(amount.toString()))
    }

}