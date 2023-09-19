package com.tasnim.chowdhury.eee.ui.incomeExpense

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.data.viewModel.IncomeExpenseViewModel
import com.tasnim.chowdhury.eee.databinding.FragmentBudgetBinding
import com.tasnim.chowdhury.eee.ui.incomeExpense.adapter.BudgetAdapter

class BudgetFragment : Fragment() {

    private lateinit var binding: FragmentBudgetBinding
    private lateinit var budgetAdapter: BudgetAdapter
    private lateinit var viewModel: IncomeExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return FragmentBudgetBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        initData()
        setupClicks()

    }

    private fun setupAdapter() {

        budgetAdapter = BudgetAdapter(requireContext())
        binding.budgetRv.adapter = budgetAdapter
        binding.budgetRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.budgetRv.setHasFixedSize(false)
        binding.budgetRv.itemAnimator = DefaultItemAnimator()

    }

    private fun initData() {

        viewModel = ViewModelProvider(this)[IncomeExpenseViewModel::class.java]

        viewModel.getAllBudget.observe(viewLifecycleOwner) { budget ->

            budgetAdapter.addBudget(budget)

        }

    }

    private fun setupClicks() {

        binding.addBudget.setOnClickListener {
            findNavController().navigate(R.id.action_budgetFragment_to_insertBudgetFragment)
        }

    }

}