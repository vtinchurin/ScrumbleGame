package ru.vtinch.scramblegame.load

import ru.vtinch.scramblegame.core.Navigation
import ru.vtinch.scramblegame.di.UiState
import ru.vtinch.scramblegame.load.view.button.RetryButton
import ru.vtinch.scramblegame.load.view.button.RetryButtonState
import ru.vtinch.scramblegame.load.view.errorTextView.ErrorText
import ru.vtinch.scramblegame.load.view.errorTextView.ErrorTextState
import ru.vtinch.scramblegame.load.view.progressView.LoadProgress
import ru.vtinch.scramblegame.load.view.progressView.LoadProgressState

interface LoadUiState : UiState {

    fun show(errorText: ErrorText, retryButton: RetryButton, progressUi: LoadProgress) = Unit

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

    data class Error(private val resId: Int) : Abstract(
        errorTextState = ErrorTextState.Default(resId),
        retryButtonState = RetryButtonState.Visible,
        progressState = LoadProgressState.Gone
    )

    object Success : LoadUiState {
        override fun navigate(navigate: Navigation) {
            navigate.navigateToGame()
        }
    }
}
