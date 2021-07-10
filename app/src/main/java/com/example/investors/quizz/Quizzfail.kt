package com.example.investors.quizz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.investors.R
import kotlinx.android.synthetic.main.activity_quizz_result.*
import kotlinx.android.synthetic.main.activity_quizzfail.*

class Quizzfail : AppCompatActivity() {
    @SuppressLint("SetTextI18n")

    private var UserName: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizzfail)

         UserName = intent.getStringExtra(Constants.USER_NAME)
        failedusername.text =  UserName


        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS,0)

        failedscore.text= "your score is $correctAnswers out of $totalQuestions"


        restart.setOnClickListener {
            startActivity(Intent(this,QuizzHome::class.java))
            finish()
        }
    }
}