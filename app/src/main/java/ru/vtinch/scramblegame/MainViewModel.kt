package ru.vtinch.scramblegame

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(
    private val liveDataWrapper: UiStateLiveDataWrapper.Mutable,
    private val repository: Repository,
) : UiStateLiveDataWrapper.Read {

    private lateinit var question: String
    private lateinit var answer: String
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private var processDeath = true

    fun init(firstRun: Boolean = true) {
        question = repository.getQuestion()
        answer = repository.getAnswer()
        if (firstRun) {
            processDeath = false
            Log.d("tvn95", "First run")
            liveDataWrapper.update(UiState.Initial(question))
        } else {
            liveDataWrapper.update(UiState.Empty)
            if (processDeath) {
                Log.d("tvn95", "Process Death")
                processDeath = false
            } else Log.d("tvn95", "Activity Death")
        }
    }

    fun handleUserInput(input: String) {
        if (input.length == answer.length) {
            liveDataWrapper.update(UiState.CorrectPrediction)
        } else {
            liveDataWrapper.update(UiState.IncorrectPrediction)
        }
    }

    fun check(prediction: String) {
        val answer = this.answer
        if (answer == prediction) {
            liveDataWrapper.update(UiState.CorrectAnswer(answer))
        } else {
            viewModelScope.launch {
                liveDataWrapper.update(UiState.IncorrectAnswer)
                delay(1500)
                liveDataWrapper.update(UiState.Initial(question))
            }
        }
    }

    fun skip() {
        repository.next()
        question = repository.getQuestion()
        liveDataWrapper.update(UiState.Initial(question))
    }

    fun next() {
        skip()
    }

    override fun liveData(): LiveData<UiState> {
        return liveDataWrapper.liveData()
    }

}