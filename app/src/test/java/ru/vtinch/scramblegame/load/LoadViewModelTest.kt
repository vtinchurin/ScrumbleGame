package ru.vtinch.scramblegame.load

import junit.framework.TestCase.assertEquals
import org.junit.Test
import ru.vtinch.scramblegame.core.customLiveData.UiObservable
import ru.vtinch.scramblegame.core.customLiveData.UiObserver

class LoadViewModelTest {

    @Test
    fun sameFragment() {
        val repository = FakeLoadRepository.Base()
        val observable = UiObservable.Single<LoadUiState>()
        val viewModel = LoadViewModel(
            repository = repository,
            observable = observable
        )
        val fragment = LoadFragment()

        repository.expectedResult(LoadResult.Success)
        viewModel.load(isFirstRun = true)
        assertEquals(1, repository.loadCalledCount)
        viewModel.startUpdate(observer = fragment)
        assertEquals(LoadUiState.Progress, fragment.statesList.first())
        assertEquals(1, fragment.statesList.size)

        repository.returnResult()
        assertEquals(LoadUiState.Success, fragment.statesList.last())
    }

    @Test
    fun case2() {
        val repository = FakeLoadRepository.Base()
        val observable = UiObservable.Single<LoadUiState>()
        val viewModel = LoadViewModel(
            repository = repository,
            observable = observable
        )
        val fragment = LoadFragment()

        repository.expectedResult(LoadResult.Error)
        viewModel.load(isFirstRun = true)
        assertEquals(1, repository.loadCalledCount)
        viewModel.startUpdate(observer = fragment)
        assertEquals(LoadUiState.Progress, fragment.statesList.first())
        assertEquals(1, fragment.statesList.size)

        viewModel.stopUpdate()

        repository.returnResult()
        assertEquals(1, fragment.statesList.size)

        val newFragment = LoadFragment()
        viewModel.load(isFirstRun = false)
        assertEquals(1, repository.loadCalledCount)
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

    class Base : FakeLoadRepository {

        private var loadResult: LoadResult? = null
        private var loadResultCallback: (LoadResult) -> Unit = {}
        override fun expectedResult(loadResult: LoadResult) {
            this.loadResult = loadResult
        }

        var loadCalledCount = 0
        override fun load(): LoadResult {
            loadCalledCount +=1
            return loadResult!!

        }

        fun returnResult() {
            loadResultCallback.invoke(loadResult!!)
        }
    }

}
