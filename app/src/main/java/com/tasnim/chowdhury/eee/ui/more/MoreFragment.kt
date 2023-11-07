package com.tasnim.chowdhury.eee.ui.more

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import com.tasnim.chowdhury.eee.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {

    private lateinit var binding: FragmentMoreBinding
    private lateinit var sharedPreferences: SharedPreferences

    private val changeImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK) {
            val data = it.data
            val imageUri = data?.data
            binding.coverImage.setImageURI(imageUri)

            // Save the selected image URI in SharedPreferences
            saveImageUri(imageUri)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setupClicks()
    }

    private fun initData() {


    }

    private fun setupClicks() {
        binding.coverEditIcon.setOnClickListener {
            val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            changeImage.launch(pickImg)
        }

        // Load the saved image URI when the fragment is created
        val savedImageUri = loadImageUri()
        if (savedImageUri != null) {
            binding.coverImage.setImageURI(savedImageUri)
        }
    }

    private fun saveImageUri(uri: Uri?){
        val editor = sharedPreferences.edit()
        editor.putString("cover_image_uri", uri.toString())
        editor.apply()
    }

    private fun loadImageUri(): Uri? {
        val uriString = sharedPreferences.getString("cover_image_uri", null)
        return if (!uriString.isNullOrBlank()){
            Uri.parse(uriString)
        }else {
            null
        }
    }

}