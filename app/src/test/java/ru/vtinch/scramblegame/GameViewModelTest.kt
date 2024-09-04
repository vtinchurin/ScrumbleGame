package ru.vtinch.scramblegame

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.vtinch.scramblegame.core.ClearViewModel
import ru.vtinch.scramblegame.game.GameRepository
import ru.vtinch.scramblegame.game.GameUiState
import ru.vtinch.scramblegame.game.GameViewModel
import ru.vtinch.scramblegame.game.UiStateLiveDataWrapper

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


class GameViewModelTest {

    private lateinit var repository: FakeGameRepository
    private lateinit var liveDataWrapper: FakeLiveDataWrapper
    private lateinit var viewModel: GameViewModel
    private lateinit var clearViewModel: ClearViewModel

    @Before
    fun setup() {
        repository = FakeGameRepository.Base()
        liveDataWrapper = FakeLiveDataWrapper.Base()

        viewModel = GameViewModel(
            gameRepository = repository,
            liveDataWrapper = liveDataWrapper,
            clearViewModel = clearViewModel
        )
    }

    @Test
    fun initTest() {

        repository.assertCall(words = listOf("word"))

        viewModel.init()

        liveDataWrapper.actualState(GameUiState.Initial("drow"))
    }

    @Test
    fun testSkip() {
        repository.assertCall(words = listOf("word", "show"))

        viewModel.init()
        viewModel.skip()

        liveDataWrapper.actualState(GameUiState.Initial("wohs"))
    }

    @Test
    fun testHandleIncorrectUserInput1() {
        repository.assertCall(words = listOf("word", "show"))

        viewModel.init()
        viewModel.handleUserInput("qwe")

        liveDataWrapper.actualState(GameUiState.IncorrectPrediction)
    }

    @Test
    fun testHandleIncorrectUserInput2() {
        repository.assertCall(words = listOf("word", "show"))

        viewModel.init()
        viewModel.handleUserInput("qwewe")

        liveDataWrapper.actualState(GameUiState.IncorrectPrediction)
    }

    @Test
    fun testHandleCorrectUserInput() {
        repository.assertCall(words = listOf("word", "show"))

        viewModel.init()
        viewModel.handleUserInput("wdeq")

        liveDataWrapper.actualState(GameUiState.CorrectPrediction)
    }

    @Test
    fun testCorrectAnswer() {
        repository.assertCall(words = listOf("word", "show"))

        viewModel.init()
        viewModel.check("word")

        liveDataWrapper.actualState(GameUiState.CorrectAnswer("word"))
    }

//    @Test
//    fun testIncorrectAnswer() {
//        repository.assertCall(words = listOf("word", "show"))
//
//        viewModel.init()
//        viewModel.check("dwor")
//
//        liveDataWrapper.actualState(GameUiState.IncorrectAnswer)
//    }

}

interface FakeGameRepository : GameRepository {

    fun assertCall(words: List<String>)

    class Base : FakeGameRepository {

        private val list = mutableListOf<String>()
        private var index = 0

        override fun assertCall(words: List<String>) {
            list.addAll(words)
        }

        override fun getQuestion(): String {
            return list[index].reversed()
        }

        override fun getAnswer(): String {
            return list[index]
        }

        override fun next() {
            index++
            if (isLast())
                index = 0
        }

        override fun isLast():Boolean {
            return index == list.size
        }

        override fun clear() {
            TODO("Not yet implemented")
        }

        private var correct = 0
        private var skip = 0
        private var incorrect = 0
        override fun addScore() {
            correct++
        }

        override fun addSkip() {
            skip++
        }

        override fun addIncorrect() {
            incorrect++
        }
    }

}


interface FakeLiveDataWrapper : UiStateLiveDataWrapper.Mutable {

    fun actualState(state: GameUiState)

    class Base : FakeLiveDataWrapper {

        private var actual: GameUiState? = null

        override fun actualState(state: GameUiState) {
            assertEquals(state, actual)
        }

        override fun liveData(): LiveData<GameUiState> {
            throw IllegalStateException("not use in test")
        }

        override fun update(value: GameUiState) {
            actual = value
        }

    }
}
