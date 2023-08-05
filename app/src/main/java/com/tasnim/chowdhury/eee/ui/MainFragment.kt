package com.tasnim.chowdhury.eee.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.tasnim.chowdhury.eee.data.viewModel.IncomeExpenseViewModel
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.data.model.IncomeExpense
import com.tasnim.chowdhury.eee.ui.incomeExpense.adapter.IncomeExpenseAdapter
import com.tasnim.chowdhury.eee.databinding.FragmentMainBinding

class MainFragment : Fragment(){

    private lateinit var binding: FragmentMainBinding

    private lateinit var adapter: IncomeExpenseAdapter
    private lateinit var viewModel: IncomeExpenseViewModel

    private var incomeExpenseList: List<IncomeExpense> = listOf()

    private var income: Float = 0.0F
    private var expense: Float = 0.0F

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
            if (incomeExpense.isEmpty()){
                binding.noDataFound.visibility = View.VISIBLE
            }else{
                binding.noDataFound.visibility = View.GONE
                val totalAmount = incomeExpense.sumOf { it.iEAmount ?: 0.00 }
                val totalIncomeAmount = incomeExpense
                    .filter { it.iEType == "Income" }
                    .sumOf { it.iEAmount ?: 0.00 }
                val totalExpenseAmount = incomeExpense
                    .filter { it.iEType == "Expense" }
                    .sumOf { it.iEAmount ?: 0.00 }

                val availableAmount = totalIncomeAmount - totalExpenseAmount

                binding.homeTotalBalanceValueTv.text = "BDT.${availableAmount}"
                binding.IncomeValueTv.text = "Bdt.$totalIncomeAmount"
                binding.expenseValueTv.text = "Bdt.$totalExpenseAmount"

                income = totalIncomeAmount.toFloat()
                expense = totalExpenseAmount.toFloat()

                Log.d("chkchk", "$income $expense")

                setupPieChart()
            }
        }

        binding.addFloatButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_insertIEFragment)
        }

        /*binding.deleteAllRecord.setOnClickListener {
            deleteAllRecords()
        }*/

        /*val searchView = binding.searchView
        val search = searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        search.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || event?.keyCode == KeyEvent.KEYCODE_SEARCH){
                val query = searchView.query.toString()
                searchDatabase(query)
                searchView.clearFocus()
                true
            }else{
                false
            }
        }*/

        /*binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(searchQuery: String?): Boolean {
                if (searchQuery != null){
                    searchDatabase(searchQuery)
                }
                return true
            }

            override fun onQueryTextSubmit(searchQuery: String?): Boolean {
                if (searchQuery != null){
                    searchDatabase(searchQuery)
                }
                return true
            }
        })*/
    }

    private fun setupPieChart() {
        val list: ArrayList<PieEntry> = ArrayList()


        list.add(PieEntry(income, "Income"))
        list.add(PieEntry(expense, "Expense"))

        Log.d("chkData", "$income $expense")

        val pieDataSet = PieDataSet(list, "Balance")
        pieDataSet.colors = getColorList()
        pieDataSet.valueTextSize = 12f
        pieDataSet.valueTextColor = R.color.final_primary_black

        val pieData = PieData(pieDataSet)
        binding.homePieChart.data = pieData
        binding.homePieChart.description.text = "Income/Expense Chart"
        binding.homePieChart.centerText = "List"
        binding.homePieChart.animateY(1500)
    }

    override fun onResume() {
        super.onResume()

        setUpDateFilter()
    }

    private fun getColorList(): List<Int> {
        return listOf(
            ContextCompat.getColor(requireContext(), R.color.final_secondary_green),
            ContextCompat.getColor(requireContext(), R.color.final_other_red),
            /*ContextCompat.getColor(requireContext(), R.color.final_other_blue),
            ContextCompat.getColor(requireContext(), R.color.final_other_green),
            ContextCompat.getColor(requireContext(), R.color.final_other_purple),
            ContextCompat.getColor(requireContext(), R.color.final_primary_orange),*/
        )
    }

    private fun setUpDateFilter() {
        val filterItem = resources.getStringArray(R.array.date_filter)
        val dateFilterAdapter = ArrayAdapter(requireContext(), R.layout.date_filter_dropdown, filterItem)
        binding.dateFilter.adapter = dateFilterAdapter
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
        deleteAllDialog.setTitle("Delete All Income/Expense!")
        deleteAllDialog.setMessage("Are you sure you want to delete everything you record?")
        deleteAllDialog.create().show()
    }

    private fun setupAdapter() {
        binding.mainRv.adapter = adapter
        binding.mainRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.mainRv.setHasFixedSize(false)
        binding.mainRv.itemAnimator = DefaultItemAnimator()
    }

    private fun searchDatabase(query: String){
        val searchQuery = "%$query%"

        viewModel.searchDatabase(searchQuery).observe(
            viewLifecycleOwner
        ) { list ->
            list.let {
                adapter.addIncomeExpense(it)
            }
        }
    }

}