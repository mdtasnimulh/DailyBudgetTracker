package com.tasnim.chowdhury.eee.ui.home

import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.ui.incomeExpense.data.model.IncomeExpense
import com.tasnim.chowdhury.eee.ui.incomeExpense.data.viewModel.IncomeExpenseViewModel
import com.tasnim.chowdhury.eee.databinding.FragmentMainBinding
import com.tasnim.chowdhury.eee.ui.incomeExpense.adapter.MainFragmentAdapter
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.LocalTime

class MainFragment : Fragment(){

    private lateinit var binding: FragmentMainBinding

    private lateinit var adapter: MainFragmentAdapter
    private lateinit var viewModel: IncomeExpenseViewModel

    private var incomeExpenseList: List<IncomeExpense> = listOf()

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

    private var isLayoutMoved = false
    private var isMenuOpen = false

    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    private val currentTimeStr = LocalTime.now().format(timeFormatter)
    private val currentTime = LocalTime.parse(currentTimeStr)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return FragmentMainBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        initData()
        setupClicks()

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

    private fun setupClicks() {

        binding.homeExpenseCardLl.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("from", "mainFragment")
            bundle.putSerializable("objectList", incomeExpenseList as ArrayList<IncomeExpense>)
            findNavController().navigate(R.id.action_mainFragment_to_calendarViewFragment, bundle)
        }

        /*binding.addFloatButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_insertIEFragment)
        }*/

        binding.recentTransactionSeeMore.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_allTransactionFragment)
        }

        binding.toolBarMenuIcon.setOnClickListener {
            if (!isMenuOpen) {
                val initialX = 0f
                val finalX = 0.75f * binding.mainMenuLayout.width
                val initialMargin = 0
                val finalMargin = 150
                val duration = 500L

                val animator = ValueAnimator.ofFloat(initialX, finalX)
                animator.duration = duration
                animator.addUpdateListener { animation ->
                    val animatedValue = animation.animatedValue as Float
                    val layoutParams = binding.mainMenuLayout.layoutParams as ViewGroup.MarginLayoutParams
                    val hoverParams = binding.mainMenuLayoutHove.layoutParams as ViewGroup.MarginLayoutParams
                    layoutParams.setMargins(0, initialMargin + ((finalMargin - initialMargin) * animation.animatedFraction).toInt(), 0, initialMargin + ((finalMargin - initialMargin) * animation.animatedFraction).toInt())
                    hoverParams.setMargins(0, initialMargin + ((finalMargin - initialMargin) * animation.animatedFraction).toInt(), 0, initialMargin + ((finalMargin - initialMargin) * animation.animatedFraction).toInt())
                    binding.mainMenuLayout.layoutParams = layoutParams
                    binding.mainMenuLayoutHove.layoutParams = hoverParams
                    binding.mainMenuLayout.translationX = animatedValue
                    binding.mainMenuLayoutHove.translationX = animatedValue
                    binding.toolBarMenuIcon.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.icon_close))

                    binding.mainMenuLayoutHove.visibility = View.VISIBLE
                    binding.homeNestedSv.visibility = View.GONE
                }

                animator.start()
            } else {
                val initialX = 0.75f * binding.mainMenuLayout.width
                val finalX = 0f
                val initialMargin = 150
                val finalMargin = 0
                val duration = 500L

                val animator = ValueAnimator.ofFloat(initialX, finalX)
                animator.duration = duration
                animator.addUpdateListener { animation ->
                    val animatedValue = animation.animatedValue as Float
                    val layoutParams = binding.mainMenuLayout.layoutParams as ViewGroup.MarginLayoutParams
                    val hoverParams = binding.mainMenuLayoutHove.layoutParams as ViewGroup.MarginLayoutParams
                    layoutParams.setMargins(0, initialMargin - ((initialMargin - finalMargin) * animation.animatedFraction).toInt(), 0, initialMargin - ((initialMargin - finalMargin) * animation.animatedFraction).toInt())
                    hoverParams.setMargins(0, initialMargin - ((initialMargin - finalMargin) * animation.animatedFraction).toInt(), 0, initialMargin - ((initialMargin - finalMargin) * animation.animatedFraction).toInt())
                    binding.mainMenuLayout.layoutParams = layoutParams
                    binding.mainMenuLayoutHove.layoutParams = hoverParams
                    binding.mainMenuLayout.translationX = animatedValue
                    binding.mainMenuLayoutHove.translationX = animatedValue
                    binding.toolBarMenuIcon.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_menu_two))

                    binding.mainMenuLayoutHove.visibility = View.GONE
                    binding.homeNestedSv.visibility = View.VISIBLE
                }

                animator.start()
            }

            // Toggle the menu state
            isMenuOpen = !isMenuOpen
        }

        binding.mainMenuLayoutHove.setOnClickListener {
            isMenuOpen = false
            val initialX = 0.75f * binding.mainMenuLayout.width
            val finalX = 0f
            val initialMargin = 150
            val finalMargin = 0
            val duration = 500L

            val animator = ValueAnimator.ofFloat(initialX, finalX)
            animator.duration = duration
            animator.addUpdateListener { animation ->
                val animatedValue = animation.animatedValue as Float
                val layoutParams = binding.mainMenuLayout.layoutParams as ViewGroup.MarginLayoutParams
                val hoverParams = binding.mainMenuLayoutHove.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams.setMargins(0, initialMargin - ((initialMargin - finalMargin) * animation.animatedFraction).toInt(), 0, initialMargin - ((initialMargin - finalMargin) * animation.animatedFraction).toInt())
                hoverParams.setMargins(0, initialMargin - ((initialMargin - finalMargin) * animation.animatedFraction).toInt(), 0, initialMargin - ((initialMargin - finalMargin) * animation.animatedFraction).toInt())
                binding.mainMenuLayout.layoutParams = layoutParams
                binding.mainMenuLayoutHove.layoutParams = hoverParams
                binding.mainMenuLayout.translationX = animatedValue
                binding.mainMenuLayoutHove.translationX = animatedValue
                binding.toolBarMenuIcon.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_menu_two))

                binding.mainMenuLayoutHove.visibility = View.GONE
                binding.homeNestedSv.visibility = View.VISIBLE
            }

            animator.start()
        }

    }

    private fun initData() {

        when {
            currentTime.isAfter(LocalTime.parse("00:00")) && currentTime.isBefore(LocalTime.parse("11:59")) -> {
                binding.greetingsText.text = "Good Morning"
                binding.greetingsText.setTextColor(ContextCompat.getColor(requireContext(), R.color.morningBlue))
                binding.greetingImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.morning_image))
            }

            currentTime.isAfter(LocalTime.parse("12:00")) && currentTime.isBefore(LocalTime.parse("17:59")) -> {
                binding.greetingsText.text = "Good Afternoon"
                binding.greetingsText.setTextColor(ContextCompat.getColor(requireContext(), R.color.amberOrange))
                binding.greetingImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.afternoon_image))
            }

            currentTime.isAfter(LocalTime.parse("18:00")) && currentTime.isBefore(LocalTime.parse("20:29")) -> {
                binding.greetingsText.text = "Good Evening"
                binding.greetingsText.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
                binding.greetingImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.evening_image))
            }

            currentTime.isAfter(LocalTime.parse("20:30")) && currentTime.isBefore(LocalTime.parse("23:59")) -> {
                binding.greetingsText.text = "Day is ending soon"
                binding.greetingsText.setTextColor(ContextCompat.getColor(requireContext(), R.color.nightBlue))
                binding.greetingImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.night_image))
            }

            else -> {
                binding.greetingsText.text = "Have a good day"
                binding.greetingsText.setTextColor(ContextCompat.getColor(requireContext(), R.color.amberOrange))
                binding.greetingImage.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.afternoon_image))
            }
        }

        viewModel = ViewModelProvider(this)[IncomeExpenseViewModel::class.java]

        viewModel.getAllIncomeExpense.observe(viewLifecycleOwner) { incomeExpense ->

            incomeExpenseList = incomeExpense

            adapter.addLimitedIncomeExpense(incomeExpense)

            if (incomeExpense.isEmpty()){

                binding.homeTotalBalanceValueTv.text = noData
                binding.IncomeValueTv.text = noData
                binding.expenseValueTv.text = noData

                binding.noDataChart.visibility = View.VISIBLE
                binding.noTransactionView.visibility = View.VISIBLE
                binding.homePieChart.visibility = View.GONE

                binding.homeTotalBalanceValueTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.silverGray))

                binding.incomeCardImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_income_gray))
                binding.expenseCardImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_expense_gray))
                binding.homeIncomeCardLl.background = ContextCompat.getDrawable(requireContext(), R.drawable.background_gray)
                binding.homeExpenseCardLl.background = ContextCompat.getDrawable(requireContext(), R.drawable.background_gray)
                binding.homeIncomeCardCl.background = ContextCompat.getDrawable(requireContext(), R.drawable.income_expense_inside_card_bg_gray)
                binding.homeExpenseCardCl.background = ContextCompat.getDrawable(requireContext(), R.drawable.income_expense_inside_card_bg_gray)

            }else{

                binding.noDataChart.visibility = View.GONE
                binding.noTransactionView.visibility = View.GONE
                binding.homePieChart.visibility = View.VISIBLE

                binding.incomeCardImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_income))
                binding.expenseCardImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_expense))
                binding.homeIncomeCardLl.background = ContextCompat.getDrawable(requireContext(), R.drawable.background_income)
                binding.homeExpenseCardLl.background = ContextCompat.getDrawable(requireContext(), R.drawable.background_expense)
                binding.homeIncomeCardCl.background = ContextCompat.getDrawable(requireContext(), R.drawable.income_expense_inside_card_bg)
                binding.homeExpenseCardCl.background = ContextCompat.getDrawable(requireContext(), R.drawable.income_expense_inside_card_bg)

                val totalAmount = incomeExpense.sumOf { it.iEAmount ?: 0.00 }
                val totalIncomeAmount = incomeExpense
                    .filter { it.iEType == "Income" }
                    .sumOf { it.iEAmount ?: 0.00 }
                val totalExpenseAmount = incomeExpense
                    .filter { it.iEType == "Expense" }
                    .sumOf { it.iEAmount ?: 0.00 }

                // Category wise spend amount

                val foodBalance = incomeExpense
                    .filter { it.categoryParent == "Food" && it.iEType == "Expense"}
                    .sumOf { it.iEAmount ?: 0.00 }

                val transportationBalance = incomeExpense
                    .filter { it.categoryParent == "Transportation" && it.iEType == "Expense"}
                    .sumOf { it.iEAmount ?: 0.00 }

                val housingBalance = incomeExpense
                    .filter { it.categoryParent == "Housing/Rental" && it.iEType == "Expense"}
                    .sumOf { it.iEAmount ?: 0.00 }

                val entertainmentBalance = incomeExpense
                    .filter { it.categoryParent == "Entertainment" && it.iEType == "Expense"}
                    .sumOf { it.iEAmount ?: 0.00 }

                val healthcareBalance = incomeExpense
                    .filter { it.categoryParent == "Healthcare/Family" && it.iEType == "Expense"}
                    .sumOf { it.iEAmount ?: 0.00 }

                val shoppingBalance = incomeExpense
                    .filter { it.categoryParent == "Shopping" && it.iEType == "Expense"}
                    .sumOf { it.iEAmount ?: 0.00 }

                val educationBalance = incomeExpense
                    .filter { it.categoryParent == "Education" && it.iEType == "Expense"}
                    .sumOf { it.iEAmount ?: 0.00 }

                val debtBalance = incomeExpense
                    .filter { it.categoryParent == "Debt/Tax" && it.iEType == "Expense"}
                    .sumOf { it.iEAmount ?: 0.00 }

                val savingsBalance = incomeExpense
                    .filter { it.categoryParent == "Savings" && it.iEType == "Expense"}
                    .sumOf { it.iEAmount ?: 0.00 }

                val giftBalance = incomeExpense
                    .filter { it.categoryParent == "Gifts/Donations" && it.iEType == "Expense"}
                    .sumOf { it.iEAmount ?: 0.00 }

                val travelBalance = incomeExpense
                    .filter { it.categoryParent == "Travel" && it.iEType == "Expense"}
                    .sumOf { it.iEAmount ?: 0.00 }

                val othersBalance = incomeExpense
                    .filter { it.categoryParent == "Others" && it.iEType == "Expense"}
                    .sumOf { it.iEAmount ?: 0.00 }

                val availableAmount = totalIncomeAmount - totalExpenseAmount

                binding.homeTotalBalanceValueTv.text = "৳ ${availableAmount}"
                binding.homeTotalBalanceValueTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
                binding.IncomeValueTv.text = "৳ $totalIncomeAmount"
                binding.expenseValueTv.text = "৳ $totalExpenseAmount"

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

                setupPieChart()

            }
        }

    }

    private fun setupPieChart() {
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
        pieDataSet.colors = getColorList()
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
        binding.homePieChart.data = pieData
        binding.homePieChart.description.text = ""
        binding.homePieChart.centerText = "Spending's"
        binding.homePieChart.setCenterTextSize(9f)
        binding.homePieChart.animateY(800)
        binding.homePieChart.holeRadius = 35f
        binding.homePieChart.transparentCircleRadius = 42f
        binding.homePieChart.setHoleColor(Color.parseColor("#FEFFF2"))
        binding.homePieChart.setDrawEntryLabels(false)
        binding.homePieChart.setUsePercentValues(true)
        binding.homePieChart.extraRightOffset = 60f
        binding.homePieChart.isRotationEnabled = false

        val l: Legend = binding.homePieChart.legend
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.isWordWrapEnabled = true
        l.form = Legend.LegendForm.CIRCLE
        l.setDrawInside(false)
        l.isEnabled = true
        l.xOffset = 225f

    }

    override fun onResume() {
        super.onResume()

        setUpDateFilter()
    }

    private fun getColorList(): List<Int> {
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

    private fun setupAdapter() {
        adapter = MainFragmentAdapter(requireContext(), "MainFragment")
        binding.mainRv.adapter = adapter
        binding.mainRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.mainRv.setHasFixedSize(false)
        binding.mainRv.itemAnimator = DefaultItemAnimator()

        adapter.transactionDetails = { transaction ->

            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToRecordDetailsFragment(
                    transaction
                )
            )

        }
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