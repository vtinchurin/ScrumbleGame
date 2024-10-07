package ru.vtinch.scramblegame.game

import androidx.lifecycle.LiveData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.vtinch.scramblegame.core.RunAsync
import ru.vtinch.scramblegame.di.ClearViewModel
import ru.vtinch.scramblegame.di.MyViewModel

class GameViewModel(
    private val liveDataWrapper: UiStateLiveDataWrapper.Mutable,
    private val gameRepository: GameRepository,
    private val clearViewModel: ClearViewModel,
    private val runAsync: RunAsync,
) : MyViewModel.Abstract(), UiStateLiveDataWrapper.Read {

    init {
        //Log.d("vm","create Game VM")
    }

    private lateinit var question: String
    private var processDeath = true

    fun init(firstRun: Boolean = true) {
        runAsync.handleAsync(viewModelScope,{
            question = gameRepository.getQuestion()
        }){
            if (firstRun) {
                processDeath = false
                //Log.d("tvn95", "First run")
                liveDataWrapper.update(GameUiState.Initial(question))

            } else {
                liveDataWrapper.update(GameUiState.Empty)
                if (processDeath) {
                    //Log.d("tvn95", "Process Death")
                    processDeath = false
                } else {}
                //Log.d("tvn95", "Activity Death")
            }
        }
    }

    fun handleUserInput(input: String) {
        if (input.length == question.length) {
            liveDataWrapper.update(GameUiState.CorrectPrediction)
        } else {
            liveDataWrapper.update(GameUiState.IncorrectPrediction)
        }
    }

    fun check(prediction: String) {
        if (gameRepository.checkPrediction(prediction)) {
            liveDataWrapper.update(GameUiState.CorrectAnswer(prediction))
        } else {
            viewModelScope.launch {
                liveDataWrapper.update(GameUiState.IncorrectAnswer)
                delay(1500)
                liveDataWrapper.update(GameUiState.Initial(question))
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
            liveDataWrapper.update(GameUiState.Navigate)
            gameRepository.clear()
            clearViewModel.clear(GameViewModel::class.java)
        }
        else{
            runAsync.handleAsync(viewModelScope,{
                question = gameRepository.getQuestion()
            }){
                liveDataWrapper.update(GameUiState.Initial(question))
            }
        }
    }

    override fun liveData(): LiveData<GameUiState> {
        return liveDataWrapper.liveData()
    }

}