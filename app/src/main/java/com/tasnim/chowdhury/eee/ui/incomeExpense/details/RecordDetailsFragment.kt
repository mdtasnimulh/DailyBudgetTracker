package com.tasnim.chowdhury.eee.ui.incomeExpense.details

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.data.viewModel.IncomeExpenseViewModel
import com.tasnim.chowdhury.eee.databinding.FragmentRecordDetailsBinding
import com.tasnim.chowdhury.eee.ui.MainFragmentDirections

class RecordDetailsFragment : Fragment() {

    private lateinit var binding: FragmentRecordDetailsBinding
    private val args by navArgs<RecordDetailsFragmentArgs>()
    private lateinit var viewModel: IncomeExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentRecordDetailsBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[IncomeExpenseViewModel::class.java]

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

    private fun setupClicks() {
        val data = args.recordDetails

        if (data != null){
            binding.toolBarBackIcon.setOnClickListener {
                findNavController().navigateUp()
            }

            binding.invoice.invoiceAmount.text = "৳ ${data.iEAmount.toString()}"
            binding.invoice.invoiceTitle.text = data.iETitle
            binding.invoice.invoicePaymentType.text = data.paymentMethod
            binding.invoice.invoiceCatType.text = data.iECategory
            binding.invoice.invoiceNote.text = data.iENote
            binding.invoice.invoiceDate.text = data.iEDate
            binding.invoice.invoiceTime.text = data.iETimeStamp
            binding.invoice.invoiceTransactionType.text = data.iEType

            binding.invoice.invoiceCategoryIcon.setImageResource(data.categoryIcon)

            if (data.iEType == "Income"){
                binding.invoice.invoiceTransactionType.setTextColor(Color.parseColor("#12A518"))
                binding.invoice.invoiceTransactionType.setBackgroundResource(R.drawable.transaction_type_green)
                binding.invoice.invoiceTransactionType.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(requireContext(), R.drawable.ic_type_green), null, null, null)
            }else{
                binding.invoice.invoiceTransactionType.setTextColor(Color.parseColor("#FF4E40"))
                binding.invoice.invoiceTransactionType.setBackgroundResource(R.drawable.transaction_type_red)
                binding.invoice.invoiceTransactionType.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(requireContext(), R.drawable.ic_type_red), null, null, null)
            }

            binding.invoice.recordEditButton.setOnClickListener{
                val action = RecordDetailsFragmentDirections.actionRecordDetailsFragmentToIncomeExpenseUpdateFragment(data)
                findNavController().navigate(action)
            }
            binding.invoice.recordDeleteButton.setOnClickListener {
                deleteRecord()
            }
        }
    }

    private fun deleteRecord() {
        if (args.recordDetails != null){
            val deleteDialog = AlertDialog.Builder(requireContext())
            deleteDialog.setPositiveButton("Yes"){_, _ ->
                viewModel.deleteIncomeExpense(args.recordDetails!!)
                Toast.makeText(requireContext(), "Successfully Deleted ${args.recordDetails?.iETitle}", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
            deleteDialog.setNegativeButton("No"){_, _ ->
                deleteDialog.setOnDismissListener {
                    it.dismiss()
                }
            }
            deleteDialog.setTitle("Delete ${args.recordDetails?.iETitle}")
            deleteDialog.setMessage("Are you sure you want to delete ${args.recordDetails?.iETitle} permanently?")
            deleteDialog.create().show()
        }
    }

}