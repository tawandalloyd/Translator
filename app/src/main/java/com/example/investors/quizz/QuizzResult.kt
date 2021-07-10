package com.example.investors.quizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.investors.R
import kotlinx.android.synthetic.main.activity_quizz_result.*

class QuizzResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz_result)

        val userName = intent.getStringExtra(Constants.USER_NAME)
        username.text =  userName

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)

        score.text= "your score is $correctAnswers out of $totalQuestions"

        finish.setOnClickListener {
           startActivity(Intent(this,QuizzHome::class.java))
            finish()
        }
    }
}