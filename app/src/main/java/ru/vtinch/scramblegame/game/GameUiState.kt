package ru.vtinch.scramblegame.game

import ru.vtinch.scramblegame.core.Navigation
import ru.vtinch.scramblegame.di.UiState
import ru.vtinch.scramblegame.game.view.button.GameButton
import ru.vtinch.scramblegame.game.view.button.GameButtonState
import ru.vtinch.scramblegame.game.view.input.CustomInput
import ru.vtinch.scramblegame.game.view.input.InputState
import ru.vtinch.scramblegame.game.view.textView.QuestionText
import ru.vtinch.scramblegame.game.view.textView.QuestionTextState

interface GameUiState : UiState {

    fun show(
        text: QuestionText,
        userInput: CustomInput,
        skip: GameButton,
        next: GameButton,
        check: GameButton,
    ) = Unit

    abstract class Abstract(
        private val checkState: GameButtonState = GameButtonState.Disabled,
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
            text.update(QuestionTextState.Initial(question))
            next.update(GameButtonState.Gone)
            skip.update(GameButtonState.Enabled)
            userInput.update(InputState.Initial)
        }
    }

    object CorrectPrediction : Abstract(
        checkState = GameButtonState.Enabled
    )

    object IncorrectPrediction : Abstract()

    data class CorrectAnswer(private val answer: String) : Abstract(
        checkState = GameButtonState.Gone
    ) {
        override fun show(
            text: QuestionText,
            userInput: CustomInput,
            skip: GameButton,
            next: GameButton,
            check: GameButton,
        ) {
            super.show(text, userInput, skip, next, check)
            text.update(QuestionTextState.Correct(answer))
            skip.update(GameButtonState.Invisible)
            next.update(GameButtonState.Enabled)
            userInput.update(InputState.Correct)
        }
    }

    data object IncorrectAnswer : Abstract() {
        override fun show(
            text: QuestionText,
            userInput: CustomInput,
            skip: GameButton,
            next: GameButton,
            check: GameButton,
        ) {
            super.show(text, userInput, skip, next, check)
            text.update(QuestionTextState.Incorrect)
            userInput.update(InputState.Incorrect)
        }
    }

    object Navigate : GameUiState {

        override fun navigate(navigate: Navigation) {
            navigate.navigateToStats()
        }
    }
}