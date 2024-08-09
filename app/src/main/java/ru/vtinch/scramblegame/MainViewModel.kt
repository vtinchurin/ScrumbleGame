package ru.vtinch.scramblegame

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.vtinch.scramblegame.domain.Repository

class MainViewModel(
    private val liveDataWrapper: UiStateLiveDataWrapper.Mutable,
    private val questions: QuestionLiveDataWrapper.Mutable,
    private val repository: Repository,
) : UiStateLiveDataWrapper.Read {

    private var current : String

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    init {
        current = repository.nextWord()
        questions.update(current)
    }


    fun check(prediction: String) {
        if (questions.liveData().value == prediction) {
            liveDataWrapper.update(UiState.CorrectAnswerState)
        } else {
            liveDataWrapper.update(UiState.IncorrectAnswerState)
            viewModelScope.launch {
                delay(1500)
                liveDataWrapper.update(UiState.InitialState)
            }

        }

    }

    fun skip() {
        current = repository.nextWord()
        questions.update(current)
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