package com.tasnim.chowdhury.eee

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.shape.MaterialShapeDrawable
import com.tasnim.chowdhury.eee.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var home: Boolean = true
    private var btnVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.selectedItemId = R.id.mainFragment
        binding.bottomNavigationView.setBackgroundResource(R.drawable.bottom_nav_bg)
        binding.bottomNavigationView.outlineAmbientShadowColor = Color.parseColor("#FFFC4C02")
        binding.bottomNavigationView.outlineSpotShadowColor = Color.parseColor("#FFFC4C02")
        binding.bottomNavigationView.itemIconTintList = null

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainHostFragment) as NavHostFragment
        val navController = navHostFragment.findNavController()

        //binding.bottomNavigationView.setupWithNavController(navController)

        binding.bottomNavigationView.setOnItemSelectedListener { menu ->
            when(menu.itemId){
                R.id.mainFragment -> {
                    home = true
                    navController.popBackStack(R.id.ie_nav_graph, false)
                    navController.navigate(R.id.mainFragment)
                    binding.bottomNavigationView.setBackgroundResource(R.drawable.bottom_nav_bg)
                    binding.bottomNavigationView.outlineAmbientShadowColor = Color.parseColor("#FFFC4C02")
                    binding.bottomNavigationView.outlineSpotShadowColor = Color.parseColor("#FFFC4C02")
                    true
                }
                R.id.budgetFragment -> {
                    home = false
                    navController.popBackStack(R.id.ie_nav_graph, false)
                    navController.navigate(R.id.budgetFragment)
                    binding.bottomNavigationView.setBackgroundResource(R.drawable.bottom_nav_bg_yellow)
                    binding.bottomNavigationView.outlineAmbientShadowColor = Color.parseColor("#FFFFA400")
                    binding.bottomNavigationView.outlineSpotShadowColor = Color.parseColor("#FFFFA400")
                    true
                }
                R.id.stateViewFragment -> {
                    home = false
                    navController.popBackStack(R.id.ie_nav_graph, false)
                    navController.navigate(R.id.stateViewFragment)
                    binding.bottomNavigationView.setBackgroundResource(R.drawable.bottom_nav_bg_blue)
                    binding.bottomNavigationView.outlineAmbientShadowColor = Color.parseColor("#FF00539C")
                    binding.bottomNavigationView.outlineSpotShadowColor = Color.parseColor("#FF00539C")
                    true
                }
                R.id.moreFragment -> {
                    home = false
                    navController.popBackStack(R.id.ie_nav_graph, false)
                    navController.navigate(R.id.moreFragment)
                    binding.bottomNavigationView.setBackgroundResource(R.drawable.bottom_nav_bg_red)
                    binding.bottomNavigationView.outlineAmbientShadowColor = Color.parseColor("#FFFA1E25")
                    binding.bottomNavigationView.outlineSpotShadowColor = Color.parseColor("#FFFA1E25")
                    true
                }

                else -> {
                    false
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
                    binding.bottomNavigationView.selectedItemId = R.id.mainFragment
                    binding.bottomNavigationView.setBackgroundResource(R.drawable.bottom_nav_bg)
                    binding.bottomNavigationView.outlineAmbientShadowColor = Color.parseColor("#FFFC4C02")
                    binding.bottomNavigationView.outlineSpotShadowColor = Color.parseColor("#FFFC4C02")
                }
            }
        })

        binding.showHideBtn.setOnClickListener {
            btnVisible = !btnVisible
            Log.d("chkClick", "$btnVisible")
            if (btnVisible){
                binding.addFloatButton.visibility = View.VISIBLE
            }else{
                binding.addFloatButton.visibility = View.GONE
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.mainFragment || destination.id == R.id.budgetFragment
                || destination.id == R.id.stateViewFragment || destination.id == R.id.moreFragment){
                binding.bottomNavigationView.visibility = View.VISIBLE
            }else{
                binding.bottomNavigationView.visibility = View.GONE
            }

            if (destination.id == R.id.mainFragment || destination.id == R.id.budgetFragment){
                /*if (btnVisible){
                    binding.addFloatButton.visibility = View.VISIBLE
                }else{
                    binding.addFloatButton.visibility = View.GONE
                }*/
                binding.addFloatButton.visibility = View.VISIBLE

                //binding.showHideBtn.visibility = View.VISIBLE
                if (destination.id == R.id.mainFragment){
                    binding.addFloatButton.setOnClickListener {
                        navController.navigate(R.id.insertIEFragment)
                    }
                }else{
                    binding.addFloatButton.setOnClickListener {
                        navController.navigate(R.id.insertBudgetFragment)
                    }
                }
            }else{
                binding.addFloatButton.visibility = View.GONE
                //binding.showHideBtn.visibility = View.GONE
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.mainHostFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}