package com.tasnim.chowdhury.eee.ui.incomeExpense

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tasnim.chowdhury.eee.databinding.ChooseCatRvLayoutBinding

class CategoryChooseAdapter(val context: Context): RecyclerView.Adapter<CategoryChooseAdapter.CategoryChooseViewHolder>() {

    private var catList: List<ChooseCatModel> = listOf(
        ChooseCatModel(1, "Food", "#FFFFFF"),
        ChooseCatModel(2, "Transportation", "#AABBCC"),
        ChooseCatModel(3, "Housing", "#FFDDDD"),
        ChooseCatModel(4, "Utilities", "#E6E6E6"),
        ChooseCatModel(5, "Groceries", "#D1E5A9"),
        ChooseCatModel(6, "Dining Out", "#FAD02E"),
        ChooseCatModel(7, "Healthcare", "#FF9AA2"),
        ChooseCatModel(8, "Entertainment", "#C7CEEA"),
        ChooseCatModel(9, "Shopping", "#F5A623"),
        ChooseCatModel(10, "Debt Payments", "#FFCB77"),
        ChooseCatModel(11, "Savings", "#98D7C2"),
        ChooseCatModel(12, "Investments", "#B2CCFF"),
        ChooseCatModel(13, "Gifts/Donations", "#FF85A1"),
        ChooseCatModel(14, "Travel", "#D0E6E3"),
        ChooseCatModel(15, "Education", "#9DD9D2"),
        ChooseCatModel(16, "Insurance", "#7F8C8D"),
        ChooseCatModel(17, "Childcare", "#F3B6C2"),
        ChooseCatModel(18, "Pets", "#FFD700"),
        ChooseCatModel(19, "Taxes", "#C0C0C0"),
        ChooseCatModel(20, "Subscriptions", "#A4DDED"),
        ChooseCatModel(21, "Fitness", "#E29D9D"),
        ChooseCatModel(22, "Beauty", "#FFB6C1"),
        ChooseCatModel(23, "Home Improvement", "#7EC850"),
        ChooseCatModel(24, "Charity", "#FF5733"),
        ChooseCatModel(25, "Other", "#CCCCCC")
    )

    inner class CategoryChooseViewHolder(val binding: ChooseCatRvLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(chooseCategory: ChooseCatModel, position: Int) {
            binding.catId1Title.text = chooseCategory.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryChooseViewHolder {
        return CategoryChooseViewHolder(
            ChooseCatRvLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: CategoryChooseViewHolder, position: Int) {
        holder.bind(catList[position], position)
    }

}