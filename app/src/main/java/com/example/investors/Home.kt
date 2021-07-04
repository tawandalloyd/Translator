package com.example.investors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.investors.login.Login
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val email : TextView = findViewById(R.id.textView3)
        val user  : TextView = findViewById(R.id.textView8)

        val emailId = intent.getStringExtra("email")
        val userId = intent.getStringExtra("user_id")

        email.text= "Email ID :: $emailId"
        user.text= "userId:: $userId"


        button.setOnClickListener {

            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@Home,Login::class.java ))
            finish()
        }
    }
}