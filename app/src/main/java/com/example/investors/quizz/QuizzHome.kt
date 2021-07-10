package com.example.investors.quizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.investors.R
import kotlinx.android.synthetic.main.activity_quizz_home.*

class QuizzHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz_home)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        Btn_submit.setOnClickListener {
            if (user.text.toString().isEmpty()){
                Toast.makeText(this, "please enter name", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this,QuizzQuestions::class.java)
                 intent.putExtra(Constants.USER_NAME,user.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}