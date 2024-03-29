package com.tasnim.chowdhury.eee.ui.budget

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.ui.incomeExpense.data.viewModel.IncomeExpenseViewModel
import com.tasnim.chowdhury.eee.databinding.FragmentBudgetBinding
import com.tasnim.chowdhury.eee.ui.budget.adapter.BudgetAdapter

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

        viewModel = ViewModelProvider(this)[IncomeExpenseViewModel::class.java]

        setupAdapter()
        initData()
        setupClicks()

    }

    private fun setupAdapter() {

        budgetAdapter = BudgetAdapter(requireContext(), viewModel)
        binding.budgetRv.adapter = budgetAdapter
        binding.budgetRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.budgetRv.setHasFixedSize(false)
        binding.budgetRv.itemAnimator = DefaultItemAnimator()

    }

    private fun initData() {

        viewModel.getAllBudget.observe(viewLifecycleOwner) { budget ->
            budgetAdapter.addBudget(budget)
        }

        budgetAdapter.budgetDetails = { budget, spendAmount ->
            findNavController().navigate(
                BudgetFragmentDirections.actionBudgetFragmentToBudgetDetailsFragment2(
                    budget,
                    spendAmount
                )
            )
        }

    }

    private fun setupClicks() {

        /*binding.addBudget.setOnClickListener {
            findNavController().navigate(R.id.action_budgetFragment_to_insertBudgetFragment)
        }*/

    }

}