package ru.vtinch.scramblegame.game

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.vtinch.scramblegame.core.uiObservable.UiObservable
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
    private lateinit var viewModel: GameViewModel
    private lateinit var clearViewModel: ClearViewModel
    private lateinit var runAsync: FakeRunAsync
    private lateinit var observable: FakeObservable<GameUiState>

    @Before
    fun setup() {
        repository = FakeGameRepository.Base()
        observable = FakeObservable.Base()
        clearViewModel = object : ClearViewModel{
            private var clazz :Class<out MyViewModel>? = null
            override fun clear(viewModelClass: Class<out MyViewModel>) {
                clazz = viewModelClass
            }
        }
        runAsync = FakeRunAsync.Base()

        viewModel = GameViewModel(
            gameRepository = repository,
            clearViewModel = clearViewModel,
            runAsync = runAsync,
            observable = observable
        )
    }

    @Test
    fun initTest() {

        repository.assertCall(words = listOf("word"))

        viewModel.init(true)
        runAsync.returnResult()

        observable.assertState(GameUiState.Initial("drow"))
    }

    @Test
    fun testSkip() {
        repository.assertCall(words = listOf("word", "show"))

        viewModel.init()
        viewModel.skip()
        runAsync.returnResult()

        observable.assertState(GameUiState.Initial("wohs"))
    }

    @Test
    fun testHandleIncorrectUserInput1() {
        repository.assertCall(words = listOf("word", "show"))

        viewModel.init()
        viewModel.handleUserInput("qwe")


        observable.assertState(GameUiState.IncorrectPrediction)
    }

    @Test
    fun testHandleIncorrectUserInput2() {
        repository.assertCall(words = listOf("word", "show"))

        viewModel.init()
        viewModel.handleUserInput("qwewe")

        observable.assertState(GameUiState.IncorrectPrediction)
    }

    @Test
    fun testHandleCorrectUserInput() {
        repository.assertCall(words = listOf("word", "show"))

        viewModel.init()
        viewModel.handleUserInput("wdeq")

        observable.assertState(GameUiState.CorrectPrediction)
    }

    @Test
    fun testCorrectAnswer() {
        repository.assertCall(words = listOf("word", "show"))

        viewModel.init()
        runAsync.returnResult()
        viewModel.check("word")

        observable.assertState(GameUiState.CorrectAnswer("word"))

        viewModel.next()
        runAsync.returnResult()

        observable.assertState(GameUiState.Initial("wohs"))
    }

    @Test
    fun testIncorrectAnswer() {
        repository.assertCall(words = listOf("word", "show"))

        viewModel.init()
        viewModel.check("dwor")

        observable.assertState(GameUiState.IncorrectAnswer)
    }

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

interface FakeObservable<T : Any> : UiObservable<T> {

    fun assertState(state: T)

    class Base<T : Any> : FakeObservable<T>, UiObservable.Single<T>() {

        override fun assertState(state: T) {
            assertEquals(state, cachedData)
        }
    }

}