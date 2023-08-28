package com.tasnim.chowdhury.eee.ui.incomeExpense.details

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.data.model.HeaderItem
import com.tasnim.chowdhury.eee.data.model.IncomeExpense
import com.tasnim.chowdhury.eee.data.viewModel.IncomeExpenseViewModel
import com.tasnim.chowdhury.eee.databinding.FragmentAllTransactionBinding
import com.tasnim.chowdhury.eee.ui.incomeExpense.MyButton
import com.tasnim.chowdhury.eee.ui.incomeExpense.MySwipeHelper
import com.tasnim.chowdhury.eee.ui.incomeExpense.RvButtonClickListener
import com.tasnim.chowdhury.eee.ui.incomeExpense.adapter.IncomeExpenseAdapter
import com.tasnim.chowdhury.eee.ui.incomeExpense.adapter.IncomeExpenseAdapter.Companion.ITEM_TYPE_ITEM

class AllTransactionFragment : Fragment() {

    private lateinit var binding: FragmentAllTransactionBinding
    private lateinit var adapter: IncomeExpenseAdapter
    private lateinit var viewModel: IncomeExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentAllTransactionBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        initData()

        setupClicks()

    }

    override fun onResume() {
        super.onResume()

        val callBack = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callBack)
    }

    private fun initData() {
        viewModel = ViewModelProvider(this)[IncomeExpenseViewModel::class.java]
        viewModel.getAllIncomeExpense.observe(viewLifecycleOwner) { incomeExpense ->
            val sortedIncomeExpense = incomeExpense.sortedByDescending { it.iEDate }
            val groupedData: MutableList<Any> = mutableListOf()
            var currentDate: String? = null
            for (item in sortedIncomeExpense) {
                if (item.iEDate != currentDate) {
                    groupedData.add(HeaderItem(item.iEDate!!))
                    currentDate = item.iEDate
                }
                groupedData.add(item)
            }
            adapter.setGroupedData(groupedData)
            if (incomeExpense.isEmpty()) {
                binding.noDataFound.visibility = View.VISIBLE
            } else {
                binding.noDataFound.visibility = View.GONE
            }
        }
        setupTabBar()
    }

    private fun setupClicks() {
        binding.toolBarBackIcon.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.deleteAllRecord.setOnClickListener {
            deleteAllRecords()
        }
    }

    private fun setupTabBar() {
        binding.allTransactionTabLayout.getTabAt(0)?.view?.setBackgroundResource(R.drawable.custom_tab_bg)
        binding.allTransactionTabLayout.getTabAt(1)?.view?.setBackgroundResource(R.drawable.tab_unselected)
        binding.allTransactionTabLayout.getTabAt(2)?.view?.setBackgroundResource(R.drawable.tab_unselected)
        binding.allTransactionTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.view?.setBackgroundResource(R.drawable.custom_tab_bg)
                when(tab?.position){
                    0 -> {
                        viewModel.getAllIncomeExpense.observe(viewLifecycleOwner) { incomeExpense ->
                            val sortedIncomeExpense = incomeExpense.sortedByDescending { it.iEDate }
                            val groupedData: MutableList<Any> = mutableListOf()
                            var currentDate: String? = null
                            for (item in sortedIncomeExpense) {
                                if (item.iEDate != currentDate) {
                                    groupedData.add(HeaderItem(item.iEDate!!))
                                    currentDate = item.iEDate
                                }
                                groupedData.add(item)
                            }
                            adapter.setGroupedData(groupedData)
                            if (incomeExpense.isEmpty()) {
                                binding.noDataFound.visibility = View.VISIBLE
                            } else {
                                binding.noDataFound.visibility = View.GONE
                            }
                        }
                    }
                    1 -> {
                        viewModel.getAllIncomeExpense.observe(viewLifecycleOwner) { incomeExpense ->
                            val incomeList = incomeExpense.filter { it.iEType == "Income" }
                            val sortedIncomeExpense = incomeList.sortedByDescending { it.iEDate }
                            val groupedData: MutableList<Any> = mutableListOf()
                            var currentDate: String? = null
                            for (item in sortedIncomeExpense) {
                                if (item.iEDate != currentDate) {
                                    groupedData.add(HeaderItem(item.iEDate!!))
                                    currentDate = item.iEDate
                                }
                                groupedData.add(item)
                            }
                            adapter.setGroupedData(groupedData)
                            if (incomeExpense.isEmpty()) {
                                binding.noDataFound.visibility = View.VISIBLE
                            } else {
                                binding.noDataFound.visibility = View.GONE
                            }
                        }
                    }
                    2 -> {
                        viewModel.getAllIncomeExpense.observe(viewLifecycleOwner) { incomeExpense ->
                            val expenseList = incomeExpense.filter { it.iEType == "Expense" }
                            val sortedIncomeExpense = expenseList.sortedByDescending { it.iEDate }
                            val groupedData: MutableList<Any> = mutableListOf()
                            var currentDate: String? = null
                            for (item in sortedIncomeExpense) {
                                if (item.iEDate != currentDate) {
                                    groupedData.add(HeaderItem(item.iEDate!!))
                                    currentDate = item.iEDate
                                }
                                groupedData.add(item)
                            }
                            adapter.setGroupedData(groupedData)
                            if (incomeExpense.isEmpty()) {
                                binding.noDataFound.visibility = View.VISIBLE
                            } else {
                                binding.noDataFound.visibility = View.GONE
                            }
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.view?.setBackgroundResource(R.drawable.tab_unselected)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        for (i in 0 until binding.allTransactionTabLayout.tabCount) {
            val tab: View = (binding.allTransactionTabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val p: ViewGroup.MarginLayoutParams = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(8, 0, 8, 0)
            tab.requestLayout()
        }

    }

    private fun setupAdapter() {
        adapter = IncomeExpenseAdapter(requireContext())

        binding.allTransactionRv.adapter = adapter
        binding.allTransactionRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.allTransactionRv.setHasFixedSize(true)
        binding.allTransactionRv.itemAnimator = DefaultItemAnimator()

        val swipe = object : MySwipeHelper(requireContext(), binding.allTransactionRv, 250){
            override fun instantiateMyButton(
                viewHolder: RecyclerView.ViewHolder,
                buffer: MutableList<MyButton>
            ) {
                if (viewHolder.itemViewType == ITEM_TYPE_ITEM) {
                    buffer.add(
                        MyButton(requireContext(),
                            "Delete",
                            40,
                            0,
                            Color.parseColor("#FF3C30"),
                            object : RvButtonClickListener{
                                override fun onClick(pos: Int) {
                                    Toast.makeText(requireContext(), "_s Delete Clicked", Toast.LENGTH_SHORT)
                                        .show()
                                    Log.d("chkPos", "$pos")
                                    /*findNavController().navigate(
                                        AllTransactionFragmentDirections.actionAllTransactionFragmentToRecordDetailsFragment(data)
                                    )*/
                                }
                            })
                    )

                    buffer.add(
                        MyButton(requireContext(),
                            "Update",
                            40,
                            0,
                            Color.parseColor("#FF9502"),
                            object : RvButtonClickListener{
                                override fun onClick(pos: Int) {
                                    Toast.makeText(requireContext(), "Update Clicked", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            })
                    )
                }else {
                    print("Else View")
                }
            }

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
            deleteAllDialog.setOnDismissListener {
                it.dismiss()
            }
        }
        deleteAllDialog.setTitle("Delete All Income/Expense!")
        deleteAllDialog.setMessage("Are you sure you want to delete everything you recorded?")
        deleteAllDialog.create().show()
    }

}
