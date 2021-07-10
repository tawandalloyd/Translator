package com.example.investors.quizz

import com.example.investors.R

object Constants {

    const val USER_NAME: String ="user_name"
    const val  TOTAL_QUESTIONS: String = "total_question"
    const val  CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Questions>{
     val questionsList = ArrayList<Questions>()

        val que1 = Questions(1,
            "what country does this flag belong to",
          R.drawable.united_states,
            "united states",
            "australia",
            "canada",
            "brasil",
            1
        )
       questionsList.add(que1)

        val que2 = Questions(2,
            "what country does this flag belong to",
            R.drawable.australia,
            "united states",
            "australia",
            "canada",
            "brasil",
            2
        )
        questionsList.add(que2)

        val que3 = Questions(3,
            "what country does this flag belong to",
            R.drawable.new_zealand,
            "united states",
            "australia",
            "canada",
            "new zealand",
            4
        )
        questionsList.add(que3)

        val que4 = Questions(4,
            "what country does this flag belong to",
            R.drawable.italy,
            "united states",
            "australia",
            "italy",
            "brasil",
            3
        )
        questionsList.add(que4)

        val que5 = Questions(5,
            "what country does this flag belong to",
            R.drawable.germany,
            "germany",
            "australia",
            "canada",
            "brasil",
            1
        )
        questionsList.add(que5)

        val que6 = Questions(6,
            "what country does this flag belong to",
            R.drawable.turkey,
            "united states",
            "australia",
            "canada",
            "turkey",
            4
        )
        questionsList.add(que6)

        return questionsList

    }
}