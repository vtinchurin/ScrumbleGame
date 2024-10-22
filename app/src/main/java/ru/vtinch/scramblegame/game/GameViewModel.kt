package ru.vtinch.scramblegame.game

import kotlinx.coroutines.delay
import ru.vtinch.scramblegame.core.RunAsync
import ru.vtinch.scramblegame.core.uiObservable.UiObservable
import ru.vtinch.scramblegame.di.ClearViewModel
import ru.vtinch.scramblegame.di.MyViewModel

class GameViewModel(
    private val gameRepository: GameRepository,
    private val clearViewModel: ClearViewModel,
    runAsync: RunAsync,
    observable: UiObservable<GameUiState>,
) : MyViewModel.ObservableAsync<GameUiState>(observable, runAsync) {

    private lateinit var question: String

    fun init(firstRun: Boolean = true) {
        if (gameRepository.isLast()) {
            observable.updateUi(GameUiState.Navigate)
            clearViewModel.clear(this::class.java)
        } else
            handleAsync {
                question = gameRepository.getQuestion()
                if (firstRun)
                    GameUiState.Initial(question)
                else
                    GameUiState.Empty
            }
//            runAsync.handleAsync(viewModelScope, {
//                question = gameRepository.getQuestion()
//            }) {
//                if (firstRun) {
//                    observable.updateUi(GameUiState.Initial(question))
//                } else
//                    observable.updateUi(GameUiState.Empty)
//            }
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
            handleAsync {
                delay(1500)
                GameUiState.Initial(question)
            }
        }
    }

    fun skip() {
        gameRepository.skip()
        next()
    }

    fun next() {
        gameRepository.next()
        init()
    }
}