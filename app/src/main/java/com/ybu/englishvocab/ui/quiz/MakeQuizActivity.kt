package com.ybu.englishvocab.ui.quiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ybu.englishvocab.R
import com.ybu.englishvocab.models.Question
import com.ybu.englishvocab.service.DatabaseHelper
import com.ybu.englishvocab.ui.home.VocabDetailActivity


class MakeQuizActivity : AppCompatActivity() {

    private val TAG = "MakeQuizActivity"

    private var questionList: List<Question>? = null
    private var currentQuestion: Question? = null
    private var answerList: ArrayList<String>? = null

    private var questionId = 0
    private var answeredQsNo = 0

    private var obtainedScore = 0;

    lateinit var tvQuestionNumber: TextView
    lateinit var tvQuestion: TextView
    lateinit var rbOption1: RadioButton
    lateinit var rbOption2: RadioButton
    lateinit var rbOption3: RadioButton
    lateinit var rbOption4: RadioButton
    lateinit var btnNext: Button
    lateinit var db: DatabaseHelper
    lateinit var type: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_quiz)


        initialize();
        Log.d(TAG, "onCreate: Started.")

        if (intent.extras != null) {
            type = intent.extras!!.getString("type")!!
        }

        //Loading questionList from database.
        db = DatabaseHelper(this);
        questionList = db.getQuestions(type);

        if (questionList != null && questionList!!.size > 0) {
            currentQuestion = questionList!![questionId]

            setQuestionsView();
        }

    }

    private fun initialize() {

        //Initialization UI Fields
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber)
        tvQuestion = findViewById(R.id.tvQuestion)
        rbOption1 = findViewById(R.id.radio0)
        rbOption2 = findViewById(R.id.radio1)
        rbOption3 = findViewById(R.id.radio2)
        rbOption4 = findViewById(R.id.radio3)
        btnNext = findViewById(R.id.btnNext)

        answerList = ArrayList();

    }

    private fun setQuestionsView() {
        rbOption1.isChecked = false
        rbOption2.isChecked = false
        rbOption3.isChecked = false
        rbOption4.isChecked = false
        answeredQsNo = questionId + 1
        tvQuestionNumber.text = ("QUESTIONS " + answeredQsNo + " OF " + questionList!!.size)
        tvQuestion.text = currentQuestion!!.question
        rbOption1.text = currentQuestion!!.option1
        rbOption2.text = currentQuestion!!.option2
        rbOption3.text = currentQuestion!!.option3
        rbOption4.text = currentQuestion!!.option4
        questionId++
    }

    fun btnNextClicked(view: View) {
        if (currentQuestion != null) {
            val radioGroup = findViewById<View>(R.id.radioGroup1) as RadioGroup
            val answer: RadioButton? = findViewById(radioGroup.checkedRadioButtonId)
            Log.d(TAG, "Selected Positioned  value - " + radioGroup.checkedRadioButtonId);

            if (answer != null) {
                Log.e(TAG, currentQuestion!!.answer + " -- " + answer.text);
                //Add answer to the list
                answerList!!.add("" + answer.text);

                if (currentQuestion!!.answer!!.equals(answer.getText())) {
                    obtainedScore++;
                    Log.d(TAG, "Correct Answer");
                    Log.d(TAG, "Obtained score " + obtainedScore);
                } else {
                    Log.d(TAG, "Wrong Answer");
                }
                if (questionId < questionList!!.size) {
                    currentQuestion = questionList!![questionId];
                    if (questionId == questionList!!.size - 1) {
                        btnNext.text = "Finish"
                    }
                    setQuestionsView();
                } else {

                    Log.d(TAG, "Questions are finished.");
                    val intent = Intent(this, QuizResultActivity::class.java)
                    intent.putExtra("type", type)
                    intent.putExtra("score", obtainedScore.toString())
                    startActivity(intent)
                    finish()
                }

            } else {
                Log.e(TAG, "No Answer");
            }

            //Need to clear the checked item id
            radioGroup.clearCheck();
        }


    }


}