package com.example.geohz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import java.lang.Math.abs


private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var true_button:Button
    private lateinit var false_button:Button
    private lateinit var nextButton:ImageButton
    private lateinit var prevButton:ImageButton
    private lateinit var questionTextView: TextView


    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))

    private var currentIndex = 0
    private var resultPoint=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        true_button=findViewById(R.id.true_button)
        false_button=findViewById(R.id.false_button)
        nextButton=findViewById(R.id.next_button)
        prevButton=findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.question_text_view)

        true_button.setOnClickListener {
            checkAnswer(true)
            questionBank[currentIndex].enableButton=false
            true_button.setEnabled(questionBank[currentIndex].enableButton)
            false_button.setEnabled(questionBank[currentIndex].enableButton)
            ShowAnswer()
        }

        false_button.setOnClickListener {
            checkAnswer(false)
            questionBank[currentIndex].enableButton=false
            true_button.setEnabled(questionBank[currentIndex].enableButton)
            false_button.setEnabled(questionBank[currentIndex].enableButton)
            ShowAnswer()
        }

        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        prevButton.setOnClickListener {
            currentIndex = abs(currentIndex - 1) % questionBank.size
            updateQuestion()
        }

        questionTextView.setOnClickListener{
            updateQuestion()
        }
        updateQuestion()

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }
    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)

        true_button.setEnabled(questionBank[currentIndex].enableButton)
        false_button.setEnabled(questionBank[currentIndex].enableButton)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()

        if (userAnswer == correctAnswer) {
            resultPoint+=1
        }
    }

    private fun ShowAnswer(){
        for( (index, element) in questionBank.withIndex()){
            if (questionBank[index].enableButton==true){return}

        }
            Toast.makeText(this, resultPoint.toString()+" правильных ответов из 6", Toast.LENGTH_SHORT)
                .show()

    }

}