package com.example.investors.nav_drawer

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager

import android.icu.text.SimpleDateFormat
import android.net.Uri

import android.os.Build.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore

import android.view.MenuItem
import android.view.View
import android.widget.ImageView

import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.GravityCompat

import androidx.drawerlayout.widget.DrawerLayout
import com.example.investors.BuildConfig
import com.example.investors.R
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_welcome.*
import java.io.File
import java.io.IOException
import java.util.*

class Welcome : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var currentPhotoPath: String
     lateinit var uploadPhoto:ImageView

    private lateinit var drawerLayout: DrawerLayout
    private val REQUEST_PERMISSION = 100
    private val REQUEST_IMAGE_CAPTURE = 1
    private val REQUEST_PICK_IMAGE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_welcome)
       drawerLayout= findViewById(R.id.drawer_layout)

        uploadPhoto = findViewById(R.id.upload_photo)

        toggle = ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        navView.setNavigationItemSelectedListener (this)
        val fragment = supportFragmentManager.beginTransaction()
        fragment.replace(R.id.fragment_container, fragment_drawer_home()).commit()

    }
    @RequiresApi(VERSION_CODES.N)
    @Throws(IOException::class)
    private fun createCapturedPhoto(): File {
        val timestamp: String = SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US).format(Date())
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("PHOTO_${timestamp}",".jpg", storageDir).apply {
            currentPhotoPath = absolutePath
        }
    }

    @RequiresApi(VERSION_CODES.N)
    private fun openCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
            intent.resolveActivity(packageManager)?.also {
                Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
                    intent.resolveActivity(packageManager)?.also {
                        val photoFile: File? = try {
                            createCapturedPhoto()
                        } catch (ex: IOException) {
                            // If there is error while creating the File, it will be null
                            null
                        }
                        photoFile?.also {
                            val photoURI = FileProvider.getUriForFile(
                                this,
                                "${BuildConfig.APPLICATION_ID}.fileprovider",
                                it
                            )
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
                        }
                    }
                }


            }
        }
    }

    private fun openGallery() {
        Intent(Intent.ACTION_GET_CONTENT).also { intent ->
            intent.type = "image/*"
            intent.resolveActivity(packageManager)?.also {
                startActivityForResult(intent, REQUEST_PICK_IMAGE)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        checkCameraPermission()
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_PERMISSION)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                val uri = Uri.parse(currentPhotoPath)
                uploadPhoto.setImageURI(uri)
            }
            else if (requestCode == REQUEST_PICK_IMAGE) {
                val uri = data?.data
                uploadPhoto.setImageURI(uri)
            }
        }
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
                fragment.replace(R.id.fragment_container, fragment_drawer_home()).commit()
            }
            R.id.about -> {
                setToolbarTitle("About Us")
                val fragment = supportFragmentManager.beginTransaction()
                fragment.replace(R.id.fragment_container, fragment_drawer_aboutus()).commit()
            }
            R.id.profile -> {
                setToolbarTitle("Profile")
                val fragment = supportFragmentManager.beginTransaction()
                fragment.replace(R.id.fragment_container, fragment_drawer_profile()).commit()
            }
        }



        return true
    }
    fun setToolbarTitle(title:String){
        supportActionBar?.title = title
    }

    fun showDialog(view: View) {
        val images = arrayOf("take photo", "open gallery")
       AlertDialog.Builder(this)
            .setTitle("Upload photo")
            .setItems(images){ dialog, which ->
             when (which){
                 0 -> if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                     openCamera()
                 }
                 1 -> openGallery()
             }
            }
            .show()
    }
}