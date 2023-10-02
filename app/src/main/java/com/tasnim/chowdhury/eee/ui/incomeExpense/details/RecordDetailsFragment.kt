package com.tasnim.chowdhury.eee.ui.incomeExpense.details

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.data.viewModel.IncomeExpenseViewModel
import com.tasnim.chowdhury.eee.databinding.FragmentRecordDetailsBinding

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
            val view = layoutInflater.inflate(R.layout.delete_dialog, null)
            val dialog = androidx.appcompat.app.AlertDialog.Builder(requireContext())
            dialog.setView(view)

            val imageView = view.findViewById<ImageView>(R.id.deleteImage)
            Glide.with(requireContext()).load(R.drawable.delete_animated_image).into(imageView)

            val noButton = view.findViewById<MaterialButton>(R.id.deleteNoButton)
            val yesButton = view.findViewById<MaterialButton>(R.id.deleteYesButton)
            val message = view.findViewById<TextView>(R.id.deleteMessageTv)

            val deleteDialog = dialog.create()
            message.text = "Are you sure you want to delete ${args.recordDetails?.iETitle}?"

            noButton.setOnClickListener {
                deleteDialog.dismiss()
            }
            yesButton.setOnClickListener {
                viewModel.deleteIncomeExpense(args.recordDetails!!)
                Toast.makeText(requireContext(), "Successfully Deleted ${args.recordDetails?.iETitle}", Toast.LENGTH_SHORT).show()
                deleteDialog.dismiss()
                findNavController().popBackStack()
            }

            deleteDialog.show()
        }
    }

}