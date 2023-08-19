package com.tasnim.chowdhury.eee.ui.incomeExpense

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.databinding.FragmentCategoryChooseDialogBinding
import com.tasnim.chowdhury.eee.ui.incomeExpense.insert.IncomeExpenseListener

class CategoryChooseDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentCategoryChooseDialogBinding
    private var listener: IncomeExpenseListener? = null

    fun setCategoryTitleToInsertFragment(listener: IncomeExpenseListener){
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
        dialog.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClicks()
    }

    private fun setupClicks() {

    }

    companion object{
        const val TAG = "ChooseCategoryDialog"
    }

}