package ru.vtinch.scramblegame.game

import ru.vtinch.scramblegame.core.Navigation
import ru.vtinch.scramblegame.game.view.button.GameButton
import ru.vtinch.scramblegame.game.view.input.CustomInput
import ru.vtinch.scramblegame.game.view.input.InputState
import ru.vtinch.scramblegame.game.view.questionTextView.QuestionText
import ru.vtinch.scramblegame.game.view.questionTextView.TextUiState
import ru.vtinch.scramblegame.presentation_core.CustomViewUi

interface GameUiState {

    fun show(
        text: QuestionText,
        userInput: CustomInput,
        skip: GameButton,
        next: GameButton,
        check: GameButton,
    ) = Unit

    fun navigate(navigate: Navigation) = Unit

    abstract class Abstract(
        private val checkState: CustomViewUi = GameButton.Disabled,
    ) : GameUiState {
        override fun show(
            text: QuestionText,
            userInput: CustomInput,
            skip: GameButton,
            next: GameButton,
            check: GameButton,
        ) {
            check.update(checkState)
        }
    }

    object Empty : GameUiState

    data class Initial(private val question: String) : Abstract() {
        override fun show(
            text: QuestionText,
            userInput: CustomInput,
            skip: GameButton,
            next: GameButton,
            check: GameButton,
        ) {
            super.show(text, userInput, skip, next, check)
            text.update(TextUiState.Initial(question))
            next.update(GameButton.Gone)
            skip.update(GameButton.Enabled)
            userInput.update(InputState.Initial)
        }
    }

    object CorrectPrediction : Abstract(
        checkState = GameButton.Enabled
    ) {
        override fun show(
            text: QuestionText,
            userInput: CustomInput,
            skip: GameButton,
            next: GameButton,
            check: GameButton,
        ) {
            super.show(text, userInput, skip, next, check)
        }

    }

    object IncorrectPrediction : Abstract() {
        override fun show(
            text: QuestionText,
            userInput: CustomInput,
            skip: GameButton,
            next: GameButton,
            check: GameButton,
        ) {
            super.show(text, userInput, skip, next, check)
        }
    }

    data class CorrectAnswer(private val answer: String) : Abstract(
        checkState = GameButton.Gone
    ) {
        override fun show(
            text: QuestionText,
            userInput: CustomInput,
            skip: GameButton,
            next: GameButton,
            check: GameButton,
        ) {
            super.show(text, userInput, skip, next, check)
            text.update(TextUiState.Correct(answer))
            skip.update(GameButton.Invisible)
            next.update(GameButton.Enabled)
            userInput.update(InputState.Correct)
        }
    }

    object IncorrectAnswer : Abstract() {
        override fun show(
            text: QuestionText,
            userInput: CustomInput,
            skip: GameButton,
            next: GameButton,
            check: GameButton,
        ) {
            super.show(text, userInput, skip, next, check)
            text.update(TextUiState.Incorrect)
            userInput.update(InputState.Incorrect)
        }
    }

    object Navigate : GameUiState {

        override fun navigate(navigate: Navigation) {
            navigate.navigateToStats()
        }
    }
}