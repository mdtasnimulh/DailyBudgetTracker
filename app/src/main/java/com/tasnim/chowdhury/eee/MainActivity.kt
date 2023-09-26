package com.tasnim.chowdhury.eee

import android.content.DialogInterface.OnDismissListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.tasnim.chowdhury.eee.databinding.ActivityMainBinding
import com.tasnim.chowdhury.eee.ui.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var home: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setItemSelected(R.id.mainFragment, true)
        binding.bottomNavigationView.setBackgroundResource(R.drawable.chip_bar_bg_green)
        binding.bottomNavigationView.outlineSpotShadowColor = ContextCompat.getColor(this, R.color.backgroundGreen2)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainHostFragment) as NavHostFragment
        val navController = navHostFragment.findNavController()

        binding.bottomNavigationView.setOnItemSelectedListener { menu ->
            when(menu){
                R.id.mainFragment -> {
                    binding.bottomNavigationView.setBackgroundResource(R.drawable.chip_bar_bg_green)
                    binding.bottomNavigationView.outlineSpotShadowColor = ContextCompat.getColor(this, R.color.backgroundGreen2)
                    home = true
                    navController.popBackStack(R.id.ie_nav_graph, false)
                    navController.navigate(R.id.mainFragment)
                }
                R.id.budgetFragment -> {
                    binding.bottomNavigationView.setBackgroundResource(R.drawable.chip_bar_bg_yellow)
                    binding.bottomNavigationView.outlineSpotShadowColor = ContextCompat.getColor(this, R.color.backgroundYellow2)
                    home = false
                    navController.popBackStack(R.id.ie_nav_graph, false)
                    navController.navigate(R.id.budgetFragment)
                }
                R.id.stateViewFragment -> {
                    binding.bottomNavigationView.setBackgroundResource(R.drawable.chip_bar_bg_red)
                    binding.bottomNavigationView.outlineSpotShadowColor = ContextCompat.getColor(this, R.color.backgroundRed2)
                    home = false
                    navController.popBackStack(R.id.ie_nav_graph, false)
                    navController.navigate(R.id.stateViewFragment)
                }
                R.id.moreFragment -> {
                    binding.bottomNavigationView.setBackgroundResource(R.drawable.chip_bar_bg_blue)
                    binding.bottomNavigationView.outlineSpotShadowColor = ContextCompat.getColor(this, R.color.backgroundBlue2)
                    home = false
                    navController.popBackStack(R.id.ie_nav_graph, false)
                    navController.navigate(R.id.moreFragment)
                }
            }
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (home){
                    val view = layoutInflater.inflate(R.layout.close_dialog, null)
                    val dialog = AlertDialog.Builder(this@MainActivity)
                    dialog.setView(view)

                    val imageView = view.findViewById<ImageView>(R.id.closeImage)
                    Glide.with(this@MainActivity).load(R.drawable.close_image).into(imageView)

                    val noButton = view.findViewById<MaterialButton>(R.id.noButton)
                    val yesButton = view.findViewById<MaterialButton>(R.id.yesButton)

                    val closeDialog = dialog.create()

                    noButton.setOnClickListener {
                        closeDialog.dismiss()
                    }
                    yesButton.setOnClickListener {
                        finishAffinity()
                    }

                    closeDialog.show()
                }else{
                    navController.popBackStack(R.id.ie_nav_graph, false)
                    navController.navigate(R.id.mainFragment)
                    home = true
                    binding.bottomNavigationView.setItemSelected(R.id.mainFragment, true)
                    binding.bottomNavigationView.setBackgroundResource(R.drawable.chip_bar_bg_green)
                    binding.bottomNavigationView.outlineSpotShadowColor = ContextCompat.getColor(applicationContext, R.color.backgroundGreen2)
                }
            }
        })

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.mainFragment || destination.id == R.id.budgetFragment
                || destination.id == R.id.stateViewFragment || destination.id == R.id.moreFragment){
                binding.bottomNavigationView.visibility = View.VISIBLE
            }else{
                binding.bottomNavigationView.visibility = View.GONE
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.mainHostFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}