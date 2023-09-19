package com.tasnim.chowdhury.eee.ui.incomeExpense

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.RecyclerListener
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.data.model.HeaderItem
import com.tasnim.chowdhury.eee.data.viewModel.CategoryChooseViewModel
import com.tasnim.chowdhury.eee.data.viewModel.IncomeExpenseViewModel
import com.tasnim.chowdhury.eee.databinding.FragmentCategoryChooseDialogBinding
import com.tasnim.chowdhury.eee.ui.incomeExpense.insert.IncomeExpenseListener

class CategoryChooseDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentCategoryChooseDialogBinding
    private var listener: IncomeExpenseListener? = null
    private lateinit var categoryViewModel: CategoryChooseViewModel

    private lateinit var adapter: CategoryChooseAdapter

    fun setCategoryTitleListener(listener: IncomeExpenseListener){
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentCategoryChooseDialogBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        val contentView = requireActivity().layoutInflater.inflate(
            R.layout.fragment_category_choose_dialog,null
        )

        dialog.setContentView(contentView)

        val width = (resources.displayMetrics.widthPixels * 0.97).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.75).toInt()
        dialog.window?.setLayout(width, height)

        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryViewModel = ViewModelProvider(this)[CategoryChooseViewModel::class.java]

        setupClicks()
        setupAdapter()

        categoryViewModel.expenseCategory.observe(viewLifecycleOwner) { categoryList ->
            val sortedCategories = categoryList.sortedBy { it.catParent }
            val groupedData: MutableList<Any> = mutableListOf()
            var catHeaderName: String? = null
            for(item in sortedCategories){
                if (item.catParent != catHeaderName){
                    groupedData.add(HeaderItem(item.catParent!!))
                    catHeaderName = item.catParent
                }
                groupedData.add(item)
            }
            adapter.setGroupedData(groupedData)
        }
    }

    private fun setupAdapter() {
        adapter = CategoryChooseAdapter(requireContext())
        binding.chooseCatRv.adapter = adapter

        adapter.categoryClickListener = object : IncomeExpenseListener{
            override fun onCalculatorResultCalculated(result: String) {
                TODO("Not yet implemented")
            }

            override fun onCategoryClicked(categoryTitle: String, categoryParent: String, categoryIcon: Int, catIconBg: Int) {
                listener?.onCategoryClicked(categoryTitle, categoryParent, categoryIcon, catIconBg)
                dismiss()
            }

        }

        binding.chooseCatRv.setHasFixedSize(false)
        binding.chooseCatRv.itemAnimator = DefaultItemAnimator()

        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.chooseCatRv.layoutManager = layoutManager

        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (adapter.getItemViewType(position)) {
                    CategoryChooseAdapter.ITEM_TYPE_HEADER -> 2 // Full width for headers
                    CategoryChooseAdapter.ITEM_TYPE_ITEM -> 1 // Normal span size for items
                    else -> 1
                }
            }
        }

        categoryViewModel.getExpenseCategories()

    }

    private fun setupClicks() {

    }

    companion object{
        const val TAG = "ChooseCategoryDialog"
    }

}