package com.tasnim.chowdhury.eee.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasnim.chowdhury.eee.data.viewModel.IncomeExpenseViewModel
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.ui.incomeExpense.adapter.IncomeExpenseAdapter
import com.tasnim.chowdhury.eee.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var adapter: IncomeExpenseAdapter
    private lateinit var viewModel: IncomeExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentMainBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = IncomeExpenseAdapter(requireContext())
        setupAdapter()
        viewModel = ViewModelProvider(this)[IncomeExpenseViewModel::class.java]

        viewModel.getAllIncomeExpense.observe(viewLifecycleOwner) { incomeExpense ->
            adapter.addIncomeExpense(incomeExpense)
        }

        binding.addFloatButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_insertIEFragment)
        }

        binding.deleteAllRecord.setOnClickListener {
            deleteAllRecords()
        }
    }

    private fun deleteAllRecords() {
        val deleteAllDialog = AlertDialog.Builder(requireContext())
        deleteAllDialog.setPositiveButton("Yes"){_, _ ->
            viewModel.deleteAllIncomeExpense()
            Toast.makeText(requireContext(), "Successfully Deleted Everything!", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
        deleteAllDialog.setNegativeButton("No"){_, _ ->

        }
        deleteAllDialog.setTitle("Delete All Income/Expense")
        deleteAllDialog.setMessage("Are you sure you want to delete everything you record?")
        deleteAllDialog.create().show()
    }

    private fun setupAdapter() {
        binding.mainRv.adapter = adapter
        binding.mainRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.mainRv.setHasFixedSize(false)
        binding.mainRv.itemAnimator = DefaultItemAnimator()
    }

}