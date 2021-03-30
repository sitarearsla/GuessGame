package com.sitare.guessgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isGone
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var score = 100
    var randomNumber = (1..100).random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkButton.setOnClickListener {
            val guess = guessTextInputView.text

            if (guess.toString().isEmpty()){
                guessTextInputView.error = "Please enter a number"
            } else {
                when {
                    guess.toString().toInt() > randomNumber  -> {
                        updateScore()
                        updateTextView("Please enter a smaller number")
                    }
                    guess.toString().toInt() < randomNumber  -> {
                        updateScore()
                        updateTextView("Please enter a bigger number")
                    }
                    else ->  {
                        updateTextView("CONGRATS! ${getString(R.string.score)} $score")
                        scoreTextView.isGone = true
                        guessTextInputView.isEnabled = false
                        checkButton.isEnabled = false
                    }
                }
            }
        }
    }

    fun updateScore(){
        score -= 1
        scoreTextView.text = "${getString(R.string.score)} $score"
    }

    fun updateTextView(text:String){
        statusTextView.text = "$text"
    }


}