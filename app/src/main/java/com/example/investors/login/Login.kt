package com.example.investors.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.investors.R
import com.example.investors.nav_drawer.Welcome
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        no_account.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        signinbutton.setOnClickListener {
            val intent = Intent(this,Welcome::class.java)
            startActivity(intent)
        }
    }
}