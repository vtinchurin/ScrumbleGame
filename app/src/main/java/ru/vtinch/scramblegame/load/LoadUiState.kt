package ru.vtinch.scramblegame.load

import ru.vtinch.scramblegame.game.NavigateToGame
import ru.vtinch.scramblegame.load.view.button.RetryButton
import ru.vtinch.scramblegame.load.view.button.RetryButtonState
import ru.vtinch.scramblegame.load.view.errorTextView.ErrorText
import ru.vtinch.scramblegame.load.view.errorTextView.ErrorTextState
import ru.vtinch.scramblegame.load.view.progressView.LoadProgress
import ru.vtinch.scramblegame.load.view.progressView.LoadProgressState

interface LoadUiState {

    fun show(errorText: ErrorText, retryButton: RetryButton, progressUi: LoadProgress) = Unit
    fun navigate(navigate: NavigateToGame) = Unit

    abstract class Abstract(
        private val errorTextState: ErrorTextState,
        private val retryButtonState: RetryButtonState,
        private val progressState: LoadProgressState
    ) : LoadUiState {
        override fun show(
            errorText: ErrorText,
            retryButton: RetryButton,
            progressUi: LoadProgress
        ) {
            errorText.update(errorTextState)
            retryButton.update(retryButtonState)
            progressUi.update(progressState)
        }
    }

    object Empty : LoadUiState

    object Progress : Abstract(
        errorTextState = ErrorTextState.Gone,
        retryButtonState = RetryButtonState.Gone,
        progressState = LoadProgressState.Visible
    )

    object Error : Abstract(
        errorTextState = ErrorTextState.Default,
        retryButtonState = RetryButtonState.Visible,
        progressState = LoadProgressState.Gone
    )

    object Success : LoadUiState {
        override fun navigate(navigate: NavigateToGame) {
            navigate.navigateToGame()
        }
    }
}
