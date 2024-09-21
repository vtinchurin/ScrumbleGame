package ru.vtinch.scramblegame.game

import ru.vtinch.scramblegame.core.Navigation
import ru.vtinch.scramblegame.game.view.button.GameButton
import ru.vtinch.scramblegame.game.view.input.CustomInput
import ru.vtinch.scramblegame.game.view.input.InputState
import ru.vtinch.scramblegame.game.view.textView.QuestionText
import ru.vtinch.scramblegame.presentation_core.CustomView
import ru.vtinch.scramblegame.presentation_core.CustomViewState

interface GameUiState {

    fun show(
        text: CustomView,
        userInput: CustomInput,
        skip: CustomView,
        next: CustomView,
        check: CustomView,
    ) = Unit

    fun navigate(navigate: Navigation) = Unit

    abstract class Abstract(
        private val checkState: CustomViewState = GameButton.Disabled,
    ) : GameUiState {
        override fun show(
            text: CustomView,
            userInput: CustomInput,
            skip: CustomView,
            next: CustomView,
            check: CustomView,
        ) {
            check.update(checkState)
        }
    }

    object Empty : GameUiState

    data class Initial(private val question: String) : Abstract() {
        override fun show(
            text: CustomView,
            userInput: CustomInput,
            skip: CustomView,
            next: CustomView,
            check: CustomView,
        ) {
            super.show(text, userInput, skip, next, check)
            text.update(QuestionText.Initial(question))
            next.update(GameButton.Gone)
            skip.update(GameButton.Enabled)
            userInput.update(InputState.Initial)
        }
    }

    object CorrectPrediction : Abstract(
        checkState = GameButton.Enabled
    ) {
        override fun show(
            text: CustomView,
            userInput: CustomInput,
            skip: CustomView,
            next: CustomView,
            check: CustomView,
        ) {
            super.show(text, userInput, skip, next, check)
        }

    }

    object IncorrectPrediction : Abstract() {
        override fun show(
            text: CustomView,
            userInput: CustomInput,
            skip: CustomView,
            next: CustomView,
            check: CustomView,
        ) {
            super.show(text, userInput, skip, next, check)
        }
    }

    data class CorrectAnswer(private val answer: String) : Abstract(
        checkState = GameButton.Gone
    ) {
        override fun show(
            text: CustomView,
            userInput: CustomInput,
            skip: CustomView,
            next: CustomView,
            check: CustomView,
        ) {
            super.show(text, userInput, skip, next, check)
            text.update(QuestionText.Correct(answer))
            skip.update(GameButton.Invisible)
            next.update(GameButton.Enabled)
            userInput.update(InputState.Correct)
        }
    }

    object IncorrectAnswer : Abstract() {
        override fun show(
            text: CustomView,
            userInput: CustomInput,
            skip: CustomView,
            next: CustomView,
            check: CustomView,
        ) {
            super.show(text, userInput, skip, next, check)
            text.update(QuestionText.Incorrect)
            userInput.update(InputState.Incorrect)
        }
    }

    object Navigate : GameUiState {

        override fun navigate(navigate: Navigation) {
            navigate.navigateToStats()
        }
    }
}