package com.tasnim.chowdhury.eee.ui.incomeExpense

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.databinding.FragmentCalculatorDialogBinding
import com.tasnim.chowdhury.eee.ui.incomeExpense.insert.IncomeExpenseListener
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.ArithmeticException
import java.lang.Exception

class CalculatorDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentCalculatorDialogBinding
    private lateinit var expression: Expression
    var lastDigit = false
    var isError = false
    var lastDot = false

    private var listener: IncomeExpenseListener? = null
    fun setCalculatorResultListener(listener: IncomeExpenseListener) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentCalculatorDialogBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        val contentView = requireActivity().layoutInflater.inflate(
            R.layout.fragment_calculator_dialog,null
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

        binding.button0.setOnClickListener { onDigitClick(binding.button0) }
        binding.button1.setOnClickListener { onDigitClick(binding.button1) }
        binding.button2.setOnClickListener { onDigitClick(binding.button2) }
        binding.button3.setOnClickListener { onDigitClick(binding.button3) }
        binding.button4.setOnClickListener { onDigitClick(binding.button4) }
        binding.button5.setOnClickListener { onDigitClick(binding.button5) }
        binding.button6.setOnClickListener { onDigitClick(binding.button6) }
        binding.button7.setOnClickListener { onDigitClick(binding.button7) }
        binding.button8.setOnClickListener { onDigitClick(binding.button8) }
        binding.button9.setOnClickListener { onDigitClick(binding.button9) }
        binding.buttonDot.setOnClickListener { onDigitClick(binding.buttonDot) }

        binding.buttonClear.setOnClickListener { onClearClick(binding.buttonClear) }
        binding.buttonDelete.setOnClickListener { onBackClick(binding.buttonDelete) }

        binding.buttonAddition.setOnClickListener { onOperatorClick(binding.buttonAddition) }
        binding.buttonPercentage.setOnClickListener { onOperatorClick(binding.buttonPercentage) }
        binding.buttonSubtraction.setOnClickListener { onOperatorClick(binding.buttonSubtraction) }
        binding.buttonMultiply.setOnClickListener { onOperatorClick(binding.buttonMultiply) }
        binding.buttonDivision.setOnClickListener { onOperatorClick(binding.buttonDivision) }

        binding.buttonEqual.setOnClickListener { onEqualClick() }

        binding.buttonOk.setOnClickListener {
            if (binding.calculateResult.text.toString().isEmpty()){
                Toast.makeText(requireContext(), "Please Enter Any Amount", Toast.LENGTH_SHORT).show()
            }else {
                listener?.onCalculatorResultCalculated(binding.calculateResult.text.toString())
                dialog?.dismiss()
            }
        }

    }

    private fun onClearClick(view: View){
        binding.calculateInput.text = ""
        binding.calculateResult.text = ""
        isError = false
        lastDigit = false
        lastDot = false
    }

    private fun onEqualClick(){
        onEqual()
        binding.calculateInput.text = binding.calculateResult.text.toString()
    }

    private fun onDigitClick(view: View){
        val buttonText = (view as Button).text.toString()

        if (isError){
            binding.calculateInput.text = buttonText
            isError = false
        }else{
            if (binding.calculateInput.text.isNotEmpty() || buttonText != "."){
                binding.calculateInput.append(buttonText)
            }
        }
        lastDigit = true
        onEqual()
    }

    private fun onOperatorClick(view: View){
        if (!isError && lastDigit){
            binding.calculateInput.append((view as Button).text)
            lastDot = false
            lastDigit = false
            onEqual()
        }
    }

    private fun onBackClick(view: View){
        binding.calculateInput.text = binding.calculateInput.text.toString().dropLast(1)
        try {
            val lastChar = binding.calculateInput.text.toString().last()
            if (lastChar.isDigit()){
                onEqual()
            }
        }catch (e: Exception){
            binding.calculateResult.text = ""
            Log.e("last char error", e.toString())
        }
    }

    private fun onEqual(){
        if (lastDigit && !isError && binding.calculateInput.text.isNotEmpty()){
            val txt = binding.calculateInput.text.toString()
            expression = ExpressionBuilder(txt).build()
            try {
                val result = expression.evaluate()
                binding.calculateResult.text = result.toString()
            }catch (ex: ArithmeticException){
                Log.e("evaluate error", ex.toString())
                binding.calculateResult.text = "Error"
                isError = true
                lastDigit = false
            }
        }
    }

    companion object{
        const val TAG = "InsertAmountDialog"
    }

}