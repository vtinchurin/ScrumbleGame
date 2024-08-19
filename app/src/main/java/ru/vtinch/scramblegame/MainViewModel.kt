package ru.vtinch.scramblegame

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
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)


    fun init() {
            question = repository.getQuestion()
            liveDataWrapper.update(UiState.Initial(question))
    }

    fun handleUserInput(input: String) {
            if (input.length == question.length) {
                liveDataWrapper.update(UiState.CorrectPrediction(question))
            } else {
                liveDataWrapper.update(UiState.IncorrectPrediction(question))
            }
    }

    fun check(prediction: String) {
            val answer = repository.getAnswer()
            if (answer == prediction) {
                liveDataWrapper.update(UiState.CorrectAnswer(answer))
            } else {
                viewModelScope.launch {
                liveDataWrapper.update(UiState.IncorrectAnswer(question))
                delay(1500)
                liveDataWrapper.update(UiState.Initial(question))
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

    fun save(bundleWrapper: BundleWrapper.Save){
        liveDataWrapper.save(bundleWrapper)
    }

    fun restore(bundleWrapper: BundleWrapper.Restore){
        val state = bundleWrapper.restore()
        liveDataWrapper.update(state)
    }

}