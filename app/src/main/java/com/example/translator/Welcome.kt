package com.example.translator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

import androidx.navigation.ui.NavigationUI.navigateUp
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_welcome.*

class Welcome : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    lateinit var toggle: ActionBarDrawerToggle

    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_welcome)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener (this)
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fragment_container,fragment_drawer_home()).commit()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout.closeDrawer(GravityCompat.START)

        when(item.itemId){
            R.id.home_fragment ->{
                setToolbarTitle("Home")
                val fragment = supportFragmentManager.beginTransaction()
                fragment.replace(R.id.fragment_container,fragment_drawer_home()).commit()
            }
            R.id.about -> {
                setToolbarTitle("About Us")
                val fragment = supportFragmentManager.beginTransaction()
                fragment.replace(R.id.fragment_container,fragment_drawer_aboutus()).commit()
            }
            R.id.profile -> {
                setToolbarTitle("Profile")
                val fragment = supportFragmentManager.beginTransaction()
                fragment.replace(R.id.fragment_container,fragment_drawer_profile()).commit()
            }
        }



        return true
    }
    fun setToolbarTitle(title:String){
        supportActionBar?.title = title
    }
}