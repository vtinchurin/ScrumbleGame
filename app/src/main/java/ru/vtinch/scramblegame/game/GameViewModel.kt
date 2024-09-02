package ru.vtinch.scramblegame.game

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.vtinch.scramblegame.GameRepository
import ru.vtinch.scramblegame.UiStateLiveDataWrapper

class GameViewModel(
    private val liveDataWrapper: UiStateLiveDataWrapper.Mutable,
    private val gameRepository: GameRepository,
) : UiStateLiveDataWrapper.Read {

    private lateinit var question: String
    private lateinit var answer: String
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private var processDeath = true

    fun init(firstRun: Boolean = true) {
        question = gameRepository.getQuestion()
        Log.d("tvn", "initial question = ${gameRepository.getQuestion()}")
        answer = gameRepository.getAnswer()
        if (firstRun) {
            processDeath = false
            Log.d("tvn95", "First run")
            liveDataWrapper.update(GameUiState.Initial(question))
        } else {
            liveDataWrapper.update(GameUiState.Empty)
            if (processDeath) {
                Log.d("tvn95", "Process Death")
                processDeath = false
            } else Log.d("tvn95", "Activity Death")
        }
    }

    fun handleUserInput(input: String) {
        if (input.length == answer.length) {
            liveDataWrapper.update(GameUiState.CorrectPrediction)
        } else {
            liveDataWrapper.update(GameUiState.IncorrectPrediction)
        }
    }

    fun check(prediction: String) {
        val answer = this.answer
        if (answer == prediction) {
            gameRepository.addScore()
            liveDataWrapper.update(GameUiState.CorrectAnswer(answer))
        } else {
            gameRepository.addIncorrect()
            viewModelScope.launch {
                liveDataWrapper.update(GameUiState.IncorrectAnswer)
                delay(1500)
                liveDataWrapper.update(GameUiState.Initial(question))
            }
        }
    }

    fun skip() {
        gameRepository.addSkip()
        next()
    }

    fun next() {
        if (gameRepository.isLast()) {
            liveDataWrapper.update(GameUiState.Finish)
        }
        else{
            gameRepository.next()
            question = gameRepository.getQuestion()
            liveDataWrapper.update(GameUiState.Initial(question))
        }

    }

    override fun liveData(): LiveData<GameUiState> {
        return liveDataWrapper.liveData()
    }

}