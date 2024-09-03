package ru.vtinch.scramblegame.game

import ru.vtinch.scramblegame.NavigateToStats
import ru.vtinch.scramblegame.game.view.button.ButtonUiState
import ru.vtinch.scramblegame.game.view.button.CustomButton
import ru.vtinch.scramblegame.game.view.input.CustomInput
import ru.vtinch.scramblegame.game.view.input.InputState
import ru.vtinch.scramblegame.game.view.questionTextView.QuestionText
import ru.vtinch.scramblegame.game.view.questionTextView.TextUiState

interface GameUiState {

    fun show(
        text: QuestionText,
        userInput: CustomInput,
        skip: CustomButton,
        next: CustomButton,
        check: CustomButton,
    ) = Unit

    fun navigate(navigate: NavigateToStats) = Unit

    abstract class Abstract(
        private val checkState: ButtonUiState = ButtonUiState.Disabled,
    ) : GameUiState {
        override fun show(
            text: QuestionText,
            userInput: CustomInput,
            skip: CustomButton,
            next: CustomButton,
            check: CustomButton,
        ) {
            check.update(checkState)
        }
    }

    object Empty : GameUiState

    data class Initial(private val question: String) : Abstract() {
        override fun show(
            text: QuestionText,
            userInput: CustomInput,
            skip: CustomButton,
            next: CustomButton,
            check: CustomButton,
        ) {
            super.show(text, userInput, skip, next, check)
            text.setText(question)
            text.update(TextUiState.Initial)
            next.update(ButtonUiState.Gone)
            skip.update(ButtonUiState.Enabled)
            userInput.update(InputState.Initial)
        }
    }

    object CorrectPrediction : Abstract(
        checkState = ButtonUiState.Enabled
    ) {
        override fun show(
            text: QuestionText,
            userInput: CustomInput,
            skip: CustomButton,
            next: CustomButton,
            check: CustomButton,
        ) {
            super.show(text, userInput, skip, next, check)
        }

    }

    object IncorrectPrediction : Abstract() {
        override fun show(
            text: QuestionText,
            userInput: CustomInput,
            skip: CustomButton,
            next: CustomButton,
            check: CustomButton,
        ) {
            super.show(text, userInput, skip, next, check)
        }
    }

    data class CorrectAnswer(private val answer: String) : Abstract(
        checkState = ButtonUiState.Gone
    ) {
        override fun show(
            text: QuestionText,
            userInput: CustomInput,
            skip: CustomButton,
            next: CustomButton,
            check: CustomButton,
        ) {
            super.show(text, userInput, skip, next, check)
            text.setText(answer)
            text.update(TextUiState.Correct)
            skip.update(ButtonUiState.Invisible)
            next.update(ButtonUiState.Enabled)
            userInput.update(InputState.Correct)
        }
    }

    object IncorrectAnswer : Abstract() {
        override fun show(
            text: QuestionText,
            userInput: CustomInput,
            skip: CustomButton,
            next: CustomButton,
            check: CustomButton,
        ) {
            super.show(text, userInput, skip, next, check)
            text.update(TextUiState.Incorrect)
            userInput.update(InputState.Incorrect)
        }
    }

    object Finish : GameUiState {

        override fun navigate(navigate: NavigateToStats) {
            navigate.navigateToStats()
        }
    }
}