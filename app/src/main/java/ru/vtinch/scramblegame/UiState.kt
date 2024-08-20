package ru.vtinch.scramblegame

import ru.vtinch.scramblegame.view.button.ButtonUiState
import ru.vtinch.scramblegame.view.button.CustomButton
import ru.vtinch.scramblegame.view.questionTextView.QuestionText

interface UiState {


    fun show(
        text: QuestionText.MutableText,
        userInput: EditInput,
        skip: CustomButton,
        next: CustomButton,
        check: CustomButton,
    )

    abstract class Abstract(
        private val checkState: ButtonUiState,
    ) : UiState {
        override fun show(
            text: QuestionText.MutableText,
            userInput: EditInput,
            skip: CustomButton,
            next: CustomButton,
            check: CustomButton
        ) {
            check.update(checkState)
        }
    }

    data class Initial(private val question: String) : Abstract(
        checkState = ButtonUiState.Disabled,
    ) {

//        override fun show(binding: ActivityMainBinding) {
//
//            binding.inputEditText.isEnabled = true
//            binding.answerText.text = question
//            binding.answerText.setBackgroundResource(R.drawable.bg_gray)
//            binding.inputEditText.text = null
//            binding.inputLayout.visibility = View.VISIBLE
//            binding.skipButton.visibility = View.VISIBLE
//            binding.checkButton.visibility = View.VISIBLE
//            binding.checkButton.isEnabled = false
//            binding.nextButton.visibility = View.GONE
//
//        }

        override fun show(
            text: QuestionText.MutableText,
            userInput: Any,
            skip: CustomButton,
            next: CustomButton,
            check: CustomButton
        ) {
            super.show(text, userInput, skip, next, check)
            text.setText(question)
            text.setBg(R.drawable.bg_gray)
            check.update(ButtonUiState.Disabled)
        }

    }

    object CorrectPrediction : Abstract(
        checkState = ButtonUiState.Enabled
    ) {
        override fun show(
            text: QuestionText.MutableText,
            userInput: EditInput,
            skip: CustomButton,
            next: CustomButton,
            check: CustomButton
        ) {
            super.show(text, userInput, skip, next, check)
        }

    }

    object IncorrectPrediction : Abstract(
        checkState = ButtonUiState.Disabled
    ) {
        override fun show(
            text: QuestionText.MutableText,
            userInput: EditInput,
            skip: CustomButton,
            next: CustomButton,
            check: CustomButton
        ) {
            super.show(text, userInput, skip, next, check)
        }
    }

    data class CorrectAnswer(private val answer: String) : Abstract(
        checkState = ButtonUiState.Gone
    ) {

        override fun show(
            text: QuestionText.MutableText,
            userInput: EditInput,
            skip: CustomButton,
            next: CustomButton,
            check: CustomButton
        ) {
            super.show(text, userInput, skip, next, check)
            text.setText(answer)
            text.setBg(R.drawable.bg_green)
            skip.update(ButtonUiState.Invisible)
            next.update(ButtonUiState.Enabled)
        }
//        override fun show(binding: ActivityMainBinding) {
//            binding.answerText.text = answer
//            //binding.inputEditText.text = null
//            binding.checkButton.visibility = View.GONE
//            binding.answerText.setBackgroundResource(R.drawable.bg_green)
//            binding.inputLayout.visibility = View.INVISIBLE
//            binding.skipButton.visibility = View.INVISIBLE
//            binding.nextButton.isEnabled = true
//            binding.nextButton.visibility = View.VISIBLE
//            binding.inputEditText.clearFocus()
//        }
    }

    object IncorrectAnswer : Abstract(
        checkState = ButtonUiState.Disabled
    ) {
//        override fun show(binding: ActivityMainBinding) {
//            binding.answerText.text = question
//            binding.answerText.setBackgroundResource(R.drawable.bg_red)
//            binding.inputLayout.visibility = View.INVISIBLE
//            binding.inputEditText.text = null
//            binding.inputEditText.isEnabled = false
//            binding.inputEditText.clearFocus()
//        }

        override fun show(
            text: QuestionText.MutableText,
            userInput: EditInput,
            skip: CustomButton,
            next: CustomButton,
            check: CustomButton
        ) {
            text.setBg(R.drawable.bg_red)
        }
    }
}