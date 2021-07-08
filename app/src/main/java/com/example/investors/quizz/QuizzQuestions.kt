package com.example.investors.quizz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.investors.R

class QuizzQuestions : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz_questions)

        val questionsList = Constants.getQuestions()
        Log.i("Questions size", "${questionsList.size}")
    }
}