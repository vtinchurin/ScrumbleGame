package ru.vtinch.scramblegame.load

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import ru.vtinch.scramblegame.core.RunAsync
import ru.vtinch.scramblegame.core.uiObservable.UiObservable
import ru.vtinch.scramblegame.core.uiObservable.UiObserver
import ru.vtinch.scramblegame.di.ClearViewModel
import ru.vtinch.scramblegame.di.MyViewModel

class LoadViewModelTest {

    private lateinit var repository: FakeLoadRepository
    private lateinit var observable: UiObservable.Single<LoadUiState>
    private lateinit var viewModel: LoadViewModel
    private lateinit var fragment: LoadFragment
    private lateinit var runAsync: FakeRunAsync
    private lateinit var clearViewModel : ClearViewModel

    @Before
    fun setup() {
        repository = FakeLoadRepository.Base()
        observable = UiObservable.Single()
        runAsync = FakeRunAsync.Base()
        clearViewModel = object : ClearViewModel {
            private var clazz : Class<out MyViewModel>? = null
            override fun clear(viewModelClass: Class<out MyViewModel>) {
                clazz = viewModelClass
            }
        }
        viewModel = LoadViewModel(
            repository = repository,
            observable = observable,
            runAsync = runAsync,
            clearViewModel

        )
        fragment = LoadFragment()
    }

    @Test
    fun sameFragment() {
        repository.expectedResult(LoadResult.Success)
        viewModel.load(isFirstRun = true)
        assertEquals(1, repository.loadCalledCount())
        viewModel.startUpdate(observer = fragment)
        assertEquals(LoadUiState.Progress, fragment.statesList.first())
        assertEquals(1, fragment.statesList.size)

        runAsync.returnResult()
        assertEquals(LoadUiState.Success, fragment.statesList.last())
    }

    @Test
    fun case2() {
        repository.expectedResult(LoadResult.Error)
        viewModel.load(isFirstRun = true)
        assertEquals(1, repository.loadCalledCount())
        viewModel.startUpdate(observer = fragment)
        assertEquals(LoadUiState.Progress, fragment.statesList.first())
        assertEquals(1, fragment.statesList.size)

        viewModel.stopUpdate()

        runAsync.returnResult()
        assertEquals(1, fragment.statesList.size)

        val newFragment = LoadFragment()
        viewModel.load(isFirstRun = false)
        assertEquals(1, repository.loadCalledCount())
        viewModel.startUpdate(observer = newFragment)

        assertEquals(LoadUiState.Error, newFragment.statesList.last())
        assertEquals(1, newFragment.statesList.size)

    }
}

private class LoadFragment : UiObserver<LoadUiState> {
    val statesList = mutableListOf<LoadUiState>()
    override fun updateUi(data: LoadUiState) {
        statesList.add(data)
    }
}

private interface FakeLoadRepository : LoadRepository {

    fun expectedResult(loadResult: LoadResult)
    fun loadCalledCount(): Int

    class Base : FakeLoadRepository {

        private var loadResult: LoadResult? = null
        private var loadCalledCount = 0

        override fun expectedResult(loadResult: LoadResult) {
            this.loadResult = loadResult
        }

        override fun loadCalledCount(): Int {
            return loadCalledCount
        }

        override suspend fun load(): LoadResult {
            loadCalledCount += 1
            return loadResult!!

        }

    }

}

interface FakeRunAsync : RunAsync {
    fun returnResult()

    class Base : FakeRunAsync {

        private lateinit var result: Any
        private var cached: (Any) -> Unit = {}

        override fun <T : Any> handleAsync(
            coroutineScope: CoroutineScope,
            heavyOperation: suspend () -> T,
            uiUpdate: (T) -> Unit
        ) {
            runBlocking {
                result = heavyOperation.invoke()
                cached = uiUpdate as (Any) -> Unit
            }
        }

        override fun returnResult() {
            cached.invoke(result)
        }
    }
}