package ru.vtinch.scramblegame

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val liveDataWrapper: UiStateLiveDataWrapper.Mutable,
    private val repository: Repository,
) : UiStateLiveDataWrapper.Read {

    private var question: String = repository.getQuestion()
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private val uiState = MutableStateFlow<UiState>(UiState.InitialState(question))
    val _uiState = uiState.asStateFlow()


    fun init() {
        viewModelScope.launch {
            question = repository.getQuestion()
            liveDataWrapper.update(UiState.InitialState(question))
            uiState.collect()
        }
    }

    fun handleUserInput(input: String) {
        viewModelScope.launch {
            if (input.length == question.length) {
                liveDataWrapper.update(UiState.CorrectPrediction(question))
                uiState.emit(UiState.CorrectPrediction(question))
            } else {
                liveDataWrapper.update(UiState.IncorrectPrediction(question))
                uiState.emit(UiState.IncorrectAnswerState(question))
            }
        }
    }

    fun check(prediction: String) {
        viewModelScope.launch {
            val answer = repository.getAnswer()
            if (answer == prediction) {
                liveDataWrapper.update(UiState.CorrectAnswerState(answer))
                uiState.emit(UiState.CorrectPrediction(answer))
            } else {
                liveDataWrapper.update(UiState.IncorrectAnswerState(question))
                uiState.emit(UiState.IncorrectPrediction(question))
                delay(1500)
                uiState.emit(UiState.InitialState(question))
                liveDataWrapper.update(UiState.InitialState(question))
            }
        }
    }

    fun skip() {
        repository.next()
        init()
    }

    fun next() {
        skip()
    }

    override fun liveData(): LiveData<UiState> {
        return liveDataWrapper.liveData()
    }

}