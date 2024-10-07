package ru.vtinch.scramblegame.game

import androidx.lifecycle.LiveData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.vtinch.scramblegame.core.RunAsync
import ru.vtinch.scramblegame.di.ClearViewModel
import ru.vtinch.scramblegame.di.MyViewModel
import ru.vtinch.scramblegame.load.FakeRunAsync

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
    private lateinit var runAsync: FakeRunAsync

    @Before
    fun setup() {

        repository = FakeGameRepository.Base()
        liveDataWrapper = FakeLiveDataWrapper.Base()
        clearViewModel = object : ClearViewModel{
            private var clazz :Class<out MyViewModel>? = null
            override fun clear(viewModelClass: Class<out MyViewModel>) {
                clazz = viewModelClass
            }
        }
        runAsync = FakeRunAsync.Base()

        viewModel = GameViewModel(
            gameRepository = repository,
            liveDataWrapper = liveDataWrapper,
            clearViewModel = clearViewModel,
            runAsync = runAsync
        )
    }

    @Test
    fun initTest() {

        repository.assertCall(words = listOf("word"))

        viewModel.init(true)
        runAsync.returnResult()


        liveDataWrapper.actualState(GameUiState.Initial("drow"))
    }

    @Test
    fun testSkip() {
        repository.assertCall(words = listOf("word", "show"))

        viewModel.init()
        viewModel.skip()
        runAsync.returnResult()

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
        runAsync.returnResult()
        viewModel.check("word")

        liveDataWrapper.actualState(GameUiState.CorrectAnswer("word"))

        viewModel.next()
        runAsync.returnResult()

        liveDataWrapper.actualState(GameUiState.Initial("wohs"))
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

private interface FakeGameRepository : GameRepository {

    fun assertCall(words: List<String>)

    class Base : FakeGameRepository {

        private val list = mutableListOf<String>()
        private var index = 0
        private var skip = 0

        override fun assertCall(words: List<String>) {
            list.addAll(words)
        }

        override suspend fun getQuestion(): String {
            return list[index].reversed()
        }

        override fun checkPrediction(prediction: String): Boolean {
            return  list[index]==prediction
        }

        override fun next() {
            index++
        }

        override fun isLast():Boolean {
            return index == list.size
        }

        override fun clear() {
                index = 0
        }

        override fun skip() {
            skip++
        }

    }

}


private interface FakeLiveDataWrapper: UiStateLiveDataWrapper.Mutable {

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
