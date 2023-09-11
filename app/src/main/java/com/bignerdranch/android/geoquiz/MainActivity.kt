package com.bignerdranch.android.geoquiz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        binding.prevButton.setOnClickListener {
            updateQuestion(-1)
        }

        binding.nextButton.setOnClickListener {
            updateQuestion(1)
        }

        updateQuestion(0)
    }

    override fun onStart() { super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() { super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() { super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() { super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() { super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion(indexIncrement: Int) {
        currentIndex = (currentIndex + indexIncrement + questionBank.size) % questionBank.size
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT) .show()
    }
}
