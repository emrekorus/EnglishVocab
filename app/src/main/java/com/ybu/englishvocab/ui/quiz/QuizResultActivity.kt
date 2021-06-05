package com.ybu.englishvocab.ui.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.ybu.englishvocab.R

class QuizResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_result)

        // calling the action bar
        val actionBar: ActionBar? = supportActionBar

        if (actionBar != null) {
            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        if (intent.extras != null) {
            val type = intent.extras!!.getString("type")!!
            val score = intent.extras!!.getString("score")!!
            findViewById<TextView>(R.id.tvQuizType).text = type + " Quiz"
            findViewById<TextView>(R.id.tvScore).text = score
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    fun quitClicked(view: View) {
        super.onBackPressed()
    }
}