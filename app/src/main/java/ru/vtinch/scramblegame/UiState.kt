package ru.vtinch.scramblegame

import android.view.View
import ru.vtinch.scramblegame.databinding.ActivityMainBinding

interface UiState {


    fun show(binding: ActivityMainBinding)

    data class Initial(private val question:String) : UiState {

        override fun show(binding: ActivityMainBinding) {

            binding.inputEditText.isEnabled = true
            binding.answerText.text = question
            binding.answerText.setBackgroundResource(R.drawable.bg_gray)
            binding.inputEditText.text = null
            binding.inputLayout.visibility = View.VISIBLE
            binding.skipButton.visibility = View.VISIBLE
            binding.checkButton.visibility = View.VISIBLE
            binding.checkButton.isEnabled = false
            binding.nextButton.visibility = View.GONE

        }
    }

    data class CorrectPrediction(private val question: String): UiState{
        override fun show(binding: ActivityMainBinding) {
            binding.checkButton.isEnabled = true
        }
    }

    data class IncorrectPrediction(private val question: String): UiState{
        override fun show(binding: ActivityMainBinding) {
            binding.checkButton.isEnabled = false
        }
    }

    data class CorrectAnswer(private val answer:String) : UiState {
        override fun show(binding: ActivityMainBinding)
        {
            binding.answerText.text = answer
            binding.inputEditText.text = null
            binding.checkButton.visibility = View.GONE
            binding.answerText.setBackgroundResource(R.drawable.bg_green)
            binding.inputLayout.visibility = View.INVISIBLE
            binding.skipButton.visibility = View.INVISIBLE
            binding.nextButton.isEnabled = true
            binding.nextButton.visibility = View.VISIBLE
            binding.inputEditText.clearFocus()
        }
    }

    data class IncorrectAnswer(private val question:String) : UiState {
        override fun show(binding: ActivityMainBinding)
        {
            binding.answerText.text = question
            binding.answerText.setBackgroundResource(R.drawable.bg_red)
            binding.inputLayout.visibility = View.INVISIBLE
            binding.inputEditText.text = null
            binding.inputEditText.isEnabled = false
            binding.inputEditText.clearFocus()
        }
    }
}