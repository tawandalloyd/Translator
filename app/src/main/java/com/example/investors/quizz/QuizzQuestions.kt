package com.example.investors.quizz

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.investors.R
import kotlinx.android.synthetic.main.activity_quizz_questions.*

class QuizzQuestions : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int =1
    private var mQuestionList: ArrayList<Questions>? = null
    private var mSelectedOptionPosition: Int =0
    private var mCorrectAnswers:Int=0
    private var mUserName: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz_questions)

        mUserName= intent.getStringExtra(Constants.USER_NAME)


         mQuestionList = Constants.getQuestions()
          setQuestion()

        option_1.setOnClickListener(this)
        option2.setOnClickListener(this)
        option3.setOnClickListener(this)
        option4.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion(){

        val question = mQuestionList!![mCurrentPosition-1]

        defaultOptionsView()
        if (mCurrentPosition== mQuestionList!!.size){
            btn_submit.text = "finish"
        }else{
            btn_submit.text =  "submit"
        }

        progressBar.progress= mCurrentPosition
        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.max

        questions.text= question.question
        imageview.setImageResource(question.image)
        option_1.text=question.optionOne
        option2.text=question.optionTwo
        option3.text= question.optionThree
        option4.text=question.optionFour
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        options.add(0,option_1)
        options.add(1,option2)
        options.add(2,option3)
        options.add(3,option4)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,R.drawable.default_option)
        }


    }


    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.option_1 -> {
                selectedOptionView(option_1,1)
            }
            R.id.option2 -> {
                selectedOptionView(option2,2)
            }
            R.id.option3 -> {
                selectedOptionView(option3,3)
            }
            R.id.option4 -> {
                selectedOptionView(option4,4)
            }
            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else  -> {
                            scoreTest()
                        }
                    }
                }else {
                    val question = mQuestionList?.get(mCurrentPosition -1)
                    if (question!!.correctAnswer!= mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition,R.drawable.wrong_option)
                    }else{mCorrectAnswers++}
                    answerView(question.correctAnswer,R.drawable.correct_option)

                    if (mCurrentPosition== mQuestionList!!.size){
                        btn_submit.text = "Finish"
                    }else{
                        btn_submit.text = "Go to next question"
                    }
                    mSelectedOptionPosition =0

                }

            }
        }

    }

    private fun scoreTest (){
        if (mCorrectAnswers >=3){
            val intent = Intent(this, QuizzResult::class.java)
            intent.putExtra(Constants.USER_NAME, mUserName)
            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
            startActivity(intent)
        }else {
            val intent = Intent(this, Quizzfail::class.java)
            intent.putExtra(Constants.USER_NAME,mUserName)
            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionList!!.size)
            startActivity(intent)

        }

    }
    private fun answerView(answer:Int, drawableView:Int){
        when(answer){
            1-> {option_1.background =ContextCompat.getDrawable(this,drawableView)}
            2-> {option2.background =ContextCompat.getDrawable(this,drawableView)}
            3-> {option3.background =ContextCompat.getDrawable(this,drawableView)}
            4-> {option4.background =ContextCompat.getDrawable(this,drawableView)}
        }
    }

//function to change textview color
    private fun selectedOptionView(tv: TextView, selectedOptionNum:Int){
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#7A8089"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,R.drawable.select_option)
    }
}