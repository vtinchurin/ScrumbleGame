package ru.vtinch.scramblegame.game

import kotlinx.coroutines.delay
import ru.vtinch.scramblegame.core.RunAsync
import ru.vtinch.scramblegame.core.uiObservable.UiObservable
import ru.vtinch.scramblegame.di.ClearViewModel
import ru.vtinch.scramblegame.di.MyViewModel

class GameViewModel(
    private val gameRepository: GameRepository,
    private val clearViewModel: ClearViewModel,
    private val runAsync: RunAsync,
    observable: UiObservable<GameUiState>,
) : MyViewModel.Abstract<GameUiState>(observable) {

    private lateinit var question: String

    fun init(firstRun: Boolean = true) {
        if (firstRun) {
            runAsync.handleAsync(viewModelScope, {
                question = gameRepository.getQuestion()
            }) {
                observable.updateUi(GameUiState.Initial(question))
            }
        } else {
            observable.updateUi(GameUiState.Empty)
            }
        }


    fun handleUserInput(input: String) {
        if (input.length == question.length) {
            observable.updateUi(GameUiState.CorrectPrediction)
        } else {
            observable.updateUi(GameUiState.IncorrectPrediction)
        }
    }

    fun check(prediction: String) {
        if (gameRepository.checkPrediction(prediction)) {
            observable.updateUi(GameUiState.CorrectAnswer(prediction))
        } else {
            observable.updateUi(GameUiState.IncorrectAnswer)
            runAsync.handleAsync(viewModelScope, {
                delay(1500)
            }) {
                observable.updateUi(GameUiState.Initial(question))
            }
        }
    }

    fun skip() {
        gameRepository.skip()
        next()
    }

    fun next() {
        gameRepository.next()
        if (gameRepository.isLast()) {
            observable.updateUi(GameUiState.Navigate)
            clearViewModel.clear(GameViewModel::class.java)
        } else {
            runAsync.handleAsync(viewModelScope, {
                question = gameRepository.getQuestion()
            }) {
                observable.updateUi(GameUiState.Initial(question))
            }
        }
    }
}