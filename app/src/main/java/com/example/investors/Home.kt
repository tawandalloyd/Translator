package com.example.investors

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.investors.login.Login
import com.example.investors.models.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_home.view.*

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val email : TextView = findViewById(R.id.textView3)
        val username  : TextView = findViewById(R.id.textView8)

       // val emailId = intent.getStringExtra("email")
        //val userId = intent.getStringExtra("user_id")

        //email.text= "Email ID :: $emailId"
        //user.text= "userId:: $userId"

      //  val sharedPreferences = getSharedPreferences(Constants.PREFERENCES,Context.MODE_PRIVATE)
       // val username = sharedPreferences.getString(Constants.LOGGED_IN_USERNAME, "")!!
        //textView3.text= "hello $username"

   var details : User= User()
        if (intent.hasExtra(Constants.USER_DETAILS)){
            details = intent.getParcelableExtra(Constants.USER_DETAILS)!!
        }
        username.setText(details.firstname)
        email.setText(details.email)



        button.setOnClickListener {

            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@Home,Login::class.java ))
            finish()
        }
    }
}