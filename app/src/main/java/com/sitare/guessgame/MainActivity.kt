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
        //Log.e("Check", "$randomNumber")
        replayButton.setOnClickListener {
            replayButton.isGone = true
            scoreTextView.isGone = false
            randomNumber = (1..100).random()
            score = 100
            updateTextView("${getString(R.string.enterNumber)}")
            guessTextInputView.setText("")
            scoreTextView.text = "${getString(R.string.score)} $score"
            checkButton.isEnabled = true
            guessTextInputView.isEnabled = true
        }

        checkButton.setOnClickListener {
            val guess = guessTextInputView.text

            if (guess.toString().isEmpty()){
                guessTextInputView.error = "${getString(R.string.enterNumber)}"
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
                        replayButton.isGone = false
                    }
                }
            }
        }
    }

    private fun updateScore(){
        score -= 1
        scoreTextView.text = "${getString(R.string.score)} $score"
    }

    private fun updateTextView(text:String){
        statusTextView.text = "$text"
    }


}