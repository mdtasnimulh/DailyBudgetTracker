package com.tasnim.chowdhury.eee.ui.incomeExpense

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.data.model.IncomeExpense
import com.tasnim.chowdhury.eee.data.model.IncomeExpenseEntry
import com.tasnim.chowdhury.eee.data.viewModel.IncomeExpenseViewModel
import com.tasnim.chowdhury.eee.databinding.FragmentStateBinding
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

class StateFragment : Fragment() {

    private lateinit var binding: FragmentStateBinding
    private lateinit var viewModel: IncomeExpenseViewModel

    private var income: Float = 0.0F
    private var expense: Float = 0.0F
    private val noData: String = "৳ 0.00"
    private var foodBalance: Float = 0.0F
    private var transportationBalance: Float = 0.0F
    private var housingBalance: Float = 0.0F
    private var entertainmentBalance: Float = 0.0F
    private var healthcareBalance: Float = 0.0F
    private var shoppingBalance: Float = 0.0F
    private var educationBalance: Float = 0.0F
    private var debtBalance: Float = 0.0F
    private var savingsBalance: Float = 0.0F
    private var giftBalance: Float = 0.0F
    private var travelBalance: Float = 0.0F
    private var othersBalance: Float = 0.0F


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return FragmentStateBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    override fun onResume() {
        super.onResume()

        setUpDateFilter()
        setupCatDateFilter()
    }

    private fun initData() {

        viewModel = ViewModelProvider(this)[IncomeExpenseViewModel::class.java]

        viewModel.getAllIncomeExpense.observe(viewLifecycleOwner) { incomeExpense ->

            if (incomeExpense.isEmpty()) {

                binding.noDataChart.visibility = View.VISIBLE
                binding.catNoDataChart.visibility = View.VISIBLE
                binding.statIEPieChart.visibility = View.GONE
                binding.catPieChart.visibility = View.GONE

            } else {

                binding.noDataChart.visibility = View.GONE
                binding.catNoDataChart.visibility = View.GONE
                binding.statIEPieChart.visibility = View.VISIBLE
                binding.catPieChart.visibility = View.VISIBLE

                val totalAmount = incomeExpense.sumOf { it.iEAmount ?: 0.00 }
                val totalIncomeAmount = incomeExpense
                    .filter { it.iEType == "Income" }
                    .sumOf { it.iEAmount ?: 0.00 }
                val totalExpenseAmount = incomeExpense
                    .filter { it.iEType == "Expense" }
                    .sumOf { it.iEAmount ?: 0.00 }

                // Category wise spend amount

                val foodBalance = incomeExpense
                    .filter { it.categoryParent == "Food" && it.iEType == "Expense" }
                    .sumOf { it.iEAmount ?: 0.00 }

                val transportationBalance = incomeExpense
                    .filter { it.categoryParent == "Transportation" && it.iEType == "Expense" }
                    .sumOf { it.iEAmount ?: 0.00 }

                val housingBalance = incomeExpense
                    .filter { it.categoryParent == "Housing/Rental" && it.iEType == "Expense" }
                    .sumOf { it.iEAmount ?: 0.00 }

                val entertainmentBalance = incomeExpense
                    .filter { it.categoryParent == "Entertainment" && it.iEType == "Expense" }
                    .sumOf { it.iEAmount ?: 0.00 }

                val healthcareBalance = incomeExpense
                    .filter { it.categoryParent == "Healthcare/Family" && it.iEType == "Expense" }
                    .sumOf { it.iEAmount ?: 0.00 }

                val shoppingBalance = incomeExpense
                    .filter { it.categoryParent == "Shopping" && it.iEType == "Expense" }
                    .sumOf { it.iEAmount ?: 0.00 }

                val educationBalance = incomeExpense
                    .filter { it.categoryParent == "Education" && it.iEType == "Expense" }
                    .sumOf { it.iEAmount ?: 0.00 }

                val debtBalance = incomeExpense
                    .filter { it.categoryParent == "Debt/Tax" && it.iEType == "Expense" }
                    .sumOf { it.iEAmount ?: 0.00 }

                val savingsBalance = incomeExpense
                    .filter { it.categoryParent == "Savings" && it.iEType == "Expense" }
                    .sumOf { it.iEAmount ?: 0.00 }

                val giftBalance = incomeExpense
                    .filter { it.categoryParent == "Gifts/Donations" && it.iEType == "Expense" }
                    .sumOf { it.iEAmount ?: 0.00 }

                val travelBalance = incomeExpense
                    .filter { it.categoryParent == "Travel" && it.iEType == "Expense" }
                    .sumOf { it.iEAmount ?: 0.00 }

                val othersBalance = incomeExpense
                    .filter { it.categoryParent == "Others" && it.iEType == "Expense" }
                    .sumOf { it.iEAmount ?: 0.00 }

                val availableAmount = totalIncomeAmount - totalExpenseAmount

                income = totalIncomeAmount.toFloat()
                expense = totalExpenseAmount.toFloat()

                this.foodBalance = foodBalance.toFloat()
                this.transportationBalance = transportationBalance.toFloat()
                this.housingBalance = housingBalance.toFloat()
                this.entertainmentBalance = entertainmentBalance.toFloat()
                this.healthcareBalance = healthcareBalance.toFloat()
                this.shoppingBalance = shoppingBalance.toFloat()
                this.educationBalance = educationBalance.toFloat()
                this.debtBalance = debtBalance.toFloat()
                this.savingsBalance = savingsBalance.toFloat()
                this.giftBalance = giftBalance.toFloat()
                this.travelBalance = travelBalance.toFloat()
                this.othersBalance = othersBalance.toFloat()

                setupBalancePieChart()
                setupCatPieChart()

                setupBarChart(incomeExpense)

                setupCategoryAmounts()

            }
        }

    }

    private fun setupCategoryAmounts() {
        binding.foodAmount.text = foodBalance.toString()
        binding.transportationAmount.text = transportationBalance.toString()
        binding.housingAmount.text = housingBalance.toString()
        binding.entertainmentAmount.text = entertainmentBalance.toString()
        binding.healthcareAmount.text = healthcareBalance.toString()
        binding.shoppingAmount.text = shoppingBalance.toString()
        binding.educationAmount.text = educationBalance.toString()
        binding.debtAmount.text = debtBalance.toString()
        binding.savingsAmount.text = savingsBalance.toString()
        binding.giftAmount.text = giftBalance.toString()
        binding.travelAmount.text = travelBalance.toString()
        binding.othersAmount.text = othersBalance.toString()

        setupAmountColor()

    }

    private fun setupAmountColor() {
        if (foodBalance < 1){
            binding.foodAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.graphiteGray))
        }else{
            binding.foodAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.food_color))
            binding.foodTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.food_color))
        }

        if (transportationBalance < 1){
            binding.transportationAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.graphiteGray))
        }else{
            binding.transportationAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.transportation_color))
            binding.transportationTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.transportation_color))
        }

        if (housingBalance < 1){
            binding.housingAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.graphiteGray))
        }else{
            binding.housingAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.housing_color))
            binding.housingTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.housing_color))
        }

        if (entertainmentBalance < 1){
            binding.entertainmentAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.graphiteGray))
        }else{
            binding.entertainmentAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.entertainment_color))
            binding.entertainmentTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.entertainment_color))
        }

        if (healthcareBalance < 1){
            binding.healthcareAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.graphiteGray))
        }else{
            binding.healthcareAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.healthcare_color))
            binding.healthcareTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.healthcare_color))
        }

        if (shoppingBalance < 1){
            binding.shoppingAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.graphiteGray))
        }else{
            binding.shoppingAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.shopping_color))
            binding.shoppingTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.shopping_color))
        }

        if (educationBalance < 1){
            binding.educationAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.graphiteGray))
        }else{
            binding.educationAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.education_color))
            binding.educationTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.education_color))
        }

        if (debtBalance < 1){
            binding.debtAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.graphiteGray))
        }else{
            binding.debtAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.debt_color))
            binding.debtTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.debt_color))
        }

        if (savingsBalance < 1){
            binding.savingsAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.graphiteGray))
        }else{
            binding.savingsAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.savings_color))
            binding.savingsTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.savings_color))
        }

        if (giftBalance < 1){
            binding.giftAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.graphiteGray))
        }else{
            binding.giftAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.gift_color))
            binding.giftTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.gift_color))
        }

        if (travelBalance < 1){
            binding.travelAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.graphiteGray))
        }else{
            binding.travelAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.travel_color))
            binding.travelTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.travel_color))
        }

        if (othersBalance < 1){
            binding.othersAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.graphiteGray))
        }else{
            binding.othersAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.others_color))
            binding.othersTitle.setTextColor(ContextCompat.getColor(requireContext(), R.color.others_color))
        }
    }

    private fun setupBarChart(incomeExpense: List<IncomeExpense>) {
        val incomeExpenseMap = mutableMapOf<String, IncomeExpenseEntry>()

        incomeExpense.forEach { entry ->
            val date = entry.iEDate // Replace with the actual date property of your data
            val income = if (entry.iEType == "Income") entry.iEAmount ?: 0.0 else 0.0
            val expense = if (entry.iEType == "Expense") entry.iEAmount ?: 0.0 else 0.0

            // If an entry for this date already exists in the map, update it; otherwise, create a new entry
            if (incomeExpenseMap.containsKey(date)) {
                val existingEntry = incomeExpenseMap[date]!!
                val updatedIncome = existingEntry.income + income
                val updatedExpense = existingEntry.expense + expense
                incomeExpenseMap[date!!] = IncomeExpenseEntry(date, updatedIncome.toFloat(), updatedExpense.toFloat())
            } else {
                incomeExpenseMap[date!!] = IncomeExpenseEntry(date, income.toFloat(), expense.toFloat())
            }
        }

        val dates = incomeExpenseMap.keys.toList()
        val incomeValues = incomeExpenseMap.values.map { it.income }
        val expenseValues = incomeExpenseMap.values.map { it.expense }
        val incomeEntries = dates.indices.map { BarEntry(it.toFloat(), incomeValues[it]) }
        val expenseEntries = dates.indices.map { BarEntry(it.toFloat() + 0.5f, expenseValues[it]) }

        val incomeDataSet = BarDataSet(incomeEntries, "Income")
        incomeDataSet.color = ContextCompat.getColor(requireContext(), R.color.debt_color)

        val expenseDataSet = BarDataSet(expenseEntries, "Expense")
        expenseDataSet.color = ContextCompat.getColor(requireContext(), R.color.orange3)

        val groupSpace = 0.4f
        val barSpace = 0.03f
        val barWidth = 0.27f

        val barData = BarData(incomeDataSet, expenseDataSet)
        barData.barWidth = barWidth

        binding.statBarChart.data = barData
        binding.statBarChart.groupBars(0f, groupSpace, barSpace)

        val xAxis: XAxis = binding.statBarChart.xAxis
        xAxis.valueFormatter = object : ValueFormatter() {
            private val dateFormatter = SimpleDateFormat("MM/dd", Locale.US)

            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                val index = value.toInt()
                if (index >= 0 && index < dates.size) {
                    val date = dates[index]
                    val dateObj = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).parse(date)
                    return dateFormatter.format(dateObj!!)
                }
                return ""
            }
        }
        xAxis.position = XAxis.XAxisPosition.TOP
        xAxis.setCenterAxisLabels(true)
        xAxis.labelCount = dates.size
        xAxis.setDrawGridLines(true)
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true

        binding.statBarChart.isScaleXEnabled = true
        binding.statBarChart.isScaleYEnabled = false
        binding.statBarChart.description.text = ""
        binding.statBarChart.setVisibleXRange(0f, 5f)
        binding.statBarChart.isDragEnabled = true

        binding.statBarChart.invalidate()
    }

    private fun setupBalancePieChart() {
        val list: ArrayList<PieEntry> = ArrayList()

        if (income > 0){
            list.add(PieEntry(income, "Income"))
        }
        if (expense > 0){
            list.add(PieEntry(expense, "Expense"))
        }

        val pieDataSet = PieDataSet(list, "")
        pieDataSet.colors = getIncomeExpenseColorList()
        pieDataSet.valueTextSize = 10f
        pieDataSet.valueTextColor = Color.WHITE
        pieDataSet.setDrawValues(true)
        pieDataSet.sliceSpace = 3.0f

        val pieData = PieData(pieDataSet)
        binding.statIEPieChart.data = pieData
        binding.statIEPieChart.description.text = ""
        binding.statIEPieChart.centerText = "Income\nExpense"
        binding.statIEPieChart.animateY(800)
        binding.statIEPieChart.setHoleColor(Color.parseColor("#FEFFF2"))

        binding.statIEPieChart.setCenterTextSize(9f)
        binding.statIEPieChart.holeRadius = 35f
        binding.statIEPieChart.transparentCircleRadius = 42f
        binding.statIEPieChart.setDrawEntryLabels(true)
        binding.statIEPieChart.setUsePercentValues(false)
        binding.statIEPieChart.extraRightOffset = 60f
        binding.statIEPieChart.isRotationEnabled = true

        val l: Legend = binding.statIEPieChart.legend
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.isWordWrapEnabled = true
        l.form = Legend.LegendForm.CIRCLE
        l.setDrawInside(false)
        l.isEnabled = true
        l.xOffset = 225f
    }

    private fun setupCatPieChart() {
        val list: ArrayList<PieEntry> = ArrayList()

        if (this.savingsBalance > 0){
            list.add(PieEntry(savingsBalance, "Savings"))
        }
        if (this.foodBalance > 0){
            list.add(PieEntry(foodBalance, "Food"))
        }
        if (this.transportationBalance > 0){
            list.add(PieEntry(transportationBalance, "Transport"))
        }
        if (this.housingBalance > 0){
            list.add(PieEntry(housingBalance, "Housing"))
        }
        if (this.entertainmentBalance > 0){
            list.add(PieEntry(entertainmentBalance, "Entertainment"))
        }
        if (this.healthcareBalance > 0){
            list.add(PieEntry(healthcareBalance, "Healthcare"))
        }
        if (this.shoppingBalance > 0){
            list.add(PieEntry(shoppingBalance, "Shopping"))
        }
        if (this.educationBalance > 0){
            list.add(PieEntry(educationBalance, "Education"))
        }
        if (this.debtBalance > 0){
            list.add(PieEntry(debtBalance, "Debt"))
        }
        if (this.giftBalance > 0){
            list.add(PieEntry(giftBalance, "Gift"))
        }
        if (this.travelBalance > 0){
            list.add(PieEntry(travelBalance, "Travel"))
        }
        if (this.othersBalance > 0){
            list.add(PieEntry(othersBalance, "Other"))
        }

        Log.d("chkData", "$income $expense")

        val pieDataSet = PieDataSet(list, "")
        pieDataSet.colors = getCatColorList()
        pieDataSet.valueTextSize = 10f
        pieDataSet.valueTextColor = Color.WHITE
        pieDataSet.setDrawValues(true)
        pieDataSet.sliceSpace = 3.0f
        pieDataSet.valueFormatter = object : ValueFormatter() {
            private val formatter = NumberFormat.getPercentInstance()

            override fun getFormattedValue(value: Float) =
                formatter.format(value / 100f)
        }

        val pieData = PieData(pieDataSet)
        binding.catPieChart.data = pieData
        binding.catPieChart.description.text = ""
        binding.catPieChart.centerText = "Spending's"
        binding.catPieChart.setCenterTextSize(9f)
        binding.catPieChart.animateY(800)
        binding.catPieChart.holeRadius = 35f
        binding.catPieChart.transparentCircleRadius = 42f
        binding.catPieChart.setHoleColor(Color.parseColor("#FEFFF2"))
        binding.catPieChart.setDrawEntryLabels(false)
        binding.catPieChart.setUsePercentValues(true)
        binding.catPieChart.extraRightOffset = 60f
        binding.catPieChart.isRotationEnabled = true

        val l: Legend = binding.catPieChart.legend
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.isWordWrapEnabled = true
        l.form = Legend.LegendForm.CIRCLE
        l.setDrawInside(false)
        l.isEnabled = true
        l.xOffset = 225f

    }

    private fun getIncomeExpenseColorList(): List<Int> {
        return listOf(
            ContextCompat.getColor(requireContext(), R.color.debt_color),
            ContextCompat.getColor(requireContext(), R.color.orange3),
        )
    }

    private fun getCatColorList(): List<Int> {
        return listOf(
            ContextCompat.getColor(requireContext(), R.color.food_color),
            ContextCompat.getColor(requireContext(), R.color.transportation_color),
            ContextCompat.getColor(requireContext(), R.color.housing_color),
            ContextCompat.getColor(requireContext(), R.color.entertainment_color),
            ContextCompat.getColor(requireContext(), R.color.healthcare_color),
            ContextCompat.getColor(requireContext(), R.color.shopping_color),
            ContextCompat.getColor(requireContext(), R.color.education_color),
            ContextCompat.getColor(requireContext(), R.color.debt_color),
            ContextCompat.getColor(requireContext(), R.color.savings_color),
            ContextCompat.getColor(requireContext(), R.color.gift_color),
            ContextCompat.getColor(requireContext(), R.color.travel_color),
            ContextCompat.getColor(requireContext(), R.color.others_color),
        )
    }

    private fun setUpDateFilter() {
        val filterItem = resources.getStringArray(R.array.date_filter)
        val dateFilterAdapter = ArrayAdapter(requireContext(), R.layout.date_filter_dropdown, filterItem)
        binding.dateFilter.adapter = dateFilterAdapter
    }

    private fun setupCatDateFilter() {
        val filterItem = resources.getStringArray(R.array.date_filter)
        val dateFilterAdapter = ArrayAdapter(requireContext(), R.layout.date_filter_dropdown, filterItem)
        binding.catDateFilter.adapter = dateFilterAdapter
    }

}