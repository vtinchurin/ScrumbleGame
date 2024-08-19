package ru.vtinch.scramblegame

import androidx.lifecycle.LiveData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


class MainViewModelTest {

    lateinit var repository: FakeRepository
    lateinit var liveDataWrapper: FakeLiveDataWrapper
    lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        repository = FakeRepository.Base()
        liveDataWrapper = FakeLiveDataWrapper.Base()
        viewModel = MainViewModel(
            repository = repository,
            liveDataWrapper = liveDataWrapper
        )
    }

    @Test
    fun initTest() {

        repository.assertCall(words = listOf("word"))

        viewModel.init()

        liveDataWrapper.actualState(UiState.Initial("drow"))
    }

    @Test
    fun testSkip() {
        repository.assertCall(words = listOf("word", "show"))

        viewModel.init()
        viewModel.skip()

        liveDataWrapper.actualState(UiState.Initial("wohs"))
    }

    @Test
    fun testHandleIncorrectUserInput1() {
        repository.assertCall(words = listOf("word", "show"))

        viewModel.init()
        viewModel.handleUserInput("qwe")

        liveDataWrapper.actualState(UiState.IncorrectPrediction("drow"))
    }

    @Test
    fun testHandleIncorrectUserInput2() {
        repository.assertCall(words = listOf("word", "show"))

        viewModel.init()
        viewModel.handleUserInput("qwewe")

        liveDataWrapper.actualState(UiState.IncorrectPrediction("drow"))
    }

    @Test
    fun testHandleCorrectUserInput() {
        repository.assertCall(words = listOf("word", "show"))

        viewModel.init()
        viewModel.handleUserInput("wdeq")

        liveDataWrapper.actualState(UiState.CorrectPrediction("drow"))
    }

    @Test
    fun testCorrectAnswer() {
        repository.assertCall(words = listOf("word", "show"))

        viewModel.init()
        viewModel.check("word")

        liveDataWrapper.actualState(UiState.CorrectAnswer("word"))
    }

//    @Test
//    fun testIncorrectAnswer() {
//        repository.assertCall(words = listOf("word", "show"))
//
//        viewModel.init()
//        viewModel.check("dwor")
//
//        liveDataWrapper.actualState(UiState.IncorrectAnswer("drow"))
//    }

}

interface FakeRepository : Repository {

    fun assertCall(words: List<String>)

    class Base : FakeRepository {

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
            if (index == list.size)
                index = 0
        }
    }

}


interface FakeLiveDataWrapper : UiStateLiveDataWrapper.Mutable {

    fun actualState(state: UiState)

    class Base : FakeLiveDataWrapper {

        private var actual: UiState? = null

        override fun actualState(state: UiState) {
            assertEquals(state, actual)
        }

        override fun liveData(): LiveData<UiState> {
            TODO("Not yet implemented")
        }

        override fun update(value: UiState) {
            actual = value
        }

        override fun save(bundleWrapper: BundleWrapper.Save) {
            TODO("Not yet implemented")
        }


    }
}
