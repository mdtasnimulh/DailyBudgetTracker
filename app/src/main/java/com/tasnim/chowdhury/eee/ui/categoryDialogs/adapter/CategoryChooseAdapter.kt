package com.tasnim.chowdhury.eee.ui.categoryDialogs.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tasnim.chowdhury.eee.ui.incomeExpense.data.model.HeaderItem
import com.tasnim.chowdhury.eee.databinding.ChooseCatNameLayoutBinding
import com.tasnim.chowdhury.eee.databinding.ChooseCatRvLayoutBinding
import com.tasnim.chowdhury.eee.ui.categoryDialogs.data.model.ChooseCatModel
import com.tasnim.chowdhury.eee.ui.incomeExpense.insert.IncomeExpenseListener

class CategoryChooseAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val ITEM_TYPE_HEADER = 0
        const val ITEM_TYPE_ITEM = 1
    }

    private var catList: List<ChooseCatModel> = listOf()
    private var groupedData: List<Any> = listOf()
    var categoryClickListener: IncomeExpenseListener? = null


    inner class HeaderViewHolder(private val binding: ChooseCatNameLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(date: String){
            binding.headerText.text = date
        }
    }

    inner class ChooseCategoryViewHolder(val binding: ChooseCatRvLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(chooseCategory: ChooseCatModel, position: Int) {
            Log.d("chkCategory", "$chooseCategory")
            binding.catId1Title.text = chooseCategory.title
            binding.catId1.background = chooseCategory.background?.let { ContextCompat.getDrawable(context, it) }
            chooseCategory.catIcon?.let { binding.catId1Image.setImageResource(it) }

            binding.catId1.setOnClickListener {
                chooseCategory.title?.let { title ->
                    chooseCategory.catParent?.let { cParent ->
                        chooseCategory.catIcon?.let { cIcon ->
                            chooseCategory.background?.let { iconBg ->
                                categoryClickListener?.onCategoryClicked(
                                    title, cParent, cIcon, iconBg
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ITEM_TYPE_HEADER -> {
                HeaderViewHolder(
                    ChooseCatNameLayoutBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
            ITEM_TYPE_ITEM -> {
                ChooseCategoryViewHolder(
                    ChooseCatRvLayoutBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
            else -> {
                throw IllegalAccessException("Unknown Viewtype: $viewType")
            }
        }
    }

    override fun getItemCount(): Int {
        return groupedData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is HeaderViewHolder -> {
                val headerItem = groupedData[position] as HeaderItem
                holder.bind(headerItem.date)
            }
            is ChooseCategoryViewHolder -> {
                val category = groupedData[position] as ChooseCatModel
                holder.bind(category, position)
            }
        }
    }

    /*fun setCategory(category: List<ChooseCatModel>){
        Log.d("chkCategory", "$category")
        catList = category
        notifyDataSetChanged()
    }*/

    override fun getItemViewType(position: Int): Int {
        return if (groupedData[position] is HeaderItem){
            ITEM_TYPE_HEADER
        }else{
            ITEM_TYPE_ITEM
        }
    }

    fun setGroupedData(data: List<Any>) {
        groupedData = data
        notifyDataSetChanged()
    }

}