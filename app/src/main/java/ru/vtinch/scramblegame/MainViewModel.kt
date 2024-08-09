package ru.vtinch.scramblegame

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(
    private val liveDataWrapper: UiStateLiveDataWrapper.Mutable,
    private val questions: QuestionLiveDataWrapper.Mutable
) : UiStateLiveDataWrapper.Read {

    private var index = 0
    private val list = listOf("input", "world", "prediction", "snow", "horse","nevermore","dog")
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    init {
        questions.update(list[index])
    }


    fun check(prediction: String) {
        if (questions.liveData().value == prediction) {
            liveDataWrapper.update(UiState.CorrectAnswerState)
        } else {
            viewModelScope.launch(Dispatchers.Main) {
            liveDataWrapper.update(UiState.IncorrectAnswerState)
                delay(3000)
                liveDataWrapper.update(UiState.InitialState)
            }

        }

    }

    fun skip() {
        index++
        questions.update(list[index])
        liveDataWrapper.update(UiState.InitialState)
    }

    fun next() {
        skip()
    }

    override fun liveData(): LiveData<UiState> {
        return liveDataWrapper.liveData()
    }

    fun questions(): LiveData<String> {
        return questions.liveData()
    }
}