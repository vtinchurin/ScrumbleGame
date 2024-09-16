package ru.vtinch.scramblegame.load

import android.view.View
import android.widget.TextView
import ru.vtinch.scramblegame.game.NavigateToGame
import ru.vtinch.scramblegame.game.view.button.ButtonUiState
import ru.vtinch.scramblegame.game.view.button.MyButton
import ru.vtinch.scramblegame.load.view.progressView.LoadProgressUiState
import ru.vtinch.scramblegame.load.view.progressView.MyProgress

interface LoadUiState {

    fun show(errorText: TextView, retryButton: MyButton, progressUi: MyProgress) = Unit
    fun navigate(navigate: NavigateToGame) = Unit

    abstract class Abstract(
        //private val errorTextState:ErrorTextState,
        private val retryButtonState: ButtonUiState,
        private val progressState: LoadProgressUiState
    ) : LoadUiState {
        override fun show(errorText: TextView, retryButton: MyButton, progressUi: MyProgress) {
            //errorText.update(errorTextState)
            retryButton.update(retryButtonState)
            progressUi.update(progressState)
        }
    }

    object Empty : LoadUiState

    object Progress : Abstract(
        //errorTextState = ErrorTextState.Gone,
        retryButtonState = ButtonUiState.Gone,
        progressState = LoadProgressUiState.Visible()
    ) {
        override fun show(errorText: TextView, retryButton: MyButton, progressUi: MyProgress) {
            errorText.visibility = View.GONE
            super.show(errorText, retryButton, progressUi)
        }
    }

    object Error : Abstract(
        //errorTextState = ErrorTextState.Visible,
        retryButtonState = ButtonUiState.Enabled,
        progressState = LoadProgressUiState.Gone()
    ) {
        override fun show(errorText: TextView, retryButton: MyButton, progressUi: MyProgress) {
            errorText.visibility = View.VISIBLE
            super.show(errorText, retryButton, progressUi)
        }
    }

    object Success : LoadUiState {
        override fun navigate(navigate: NavigateToGame) {
            navigate.navigateToGame()
        }
    }
}
