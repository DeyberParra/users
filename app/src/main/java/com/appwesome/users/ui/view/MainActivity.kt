package com.appwesome.users.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.appwesome.users.R
import com.appwesome.users.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var  binding : ActivityMainBinding
    private lateinit var  bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationView = binding.navigation
        navController = findNavController(R.id.nav_host_fragment)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_users -> {
                    navController.navigate(R.id.usersFragment)
                    true}
                R.id.menu_states -> {
                    navController.navigate(R.id.stateFragment)
                    true}
                R.id.menu_mayor ->{
                    navController.navigate(R.id.mayorFragment)
                    true
                }
                else -> {
                    navController.navigate(R.id.usersFragment)
                    true}
                }
            }
        }
}