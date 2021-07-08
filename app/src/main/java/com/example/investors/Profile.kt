package com.example.investors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.investors.models.User
import kotlinx.android.synthetic.main.activity_profile.*

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        var userDetails : User = User()
        if (intent.hasExtra(Constants.USER_DETAILS)){
            userDetails = intent.getParcelableExtra(Constants.USER_DETAILS)!!
        }
        first_name.isEnabled =  false
        first_name.setText(userDetails.firstname)

        surname.isEnabled =  false
        surname.setText(userDetails.surname)
        profile_email.isEnabled =  false
        profile_email.setText(userDetails.email)

    }
}