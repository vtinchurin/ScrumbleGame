package ru.vtinch.scramblegame

import androidx.lifecycle.LiveData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.vtinch.scramblegame.di.ClearViewModel
import ru.vtinch.scramblegame.di.MyViewModel
import ru.vtinch.scramblegame.game.GameUiState
import ru.vtinch.scramblegame.stats.StatsRepository
import ru.vtinch.scramblegame.stats.StatsUiState
import ru.vtinch.scramblegame.stats.StatsUiStateLiveDataWrapper
import ru.vtinch.scramblegame.stats.StatsViewModel

class StatsViewModelTest {

    private lateinit var repository: FakeStatsRepository
    private lateinit var liveDataWrapper: FakeStatsLiveDataWrapper
    private lateinit var viewModel: StatsViewModel
    private lateinit var clearViewModel: ClearViewModel

    @Before
    fun setup() {

        repository = FakeStatsRepository.Base()
        repository.setScore(Triple(1,2,5))
        liveDataWrapper = FakeStatsLiveDataWrapper.Base()
        clearViewModel = object : ClearViewModel{
            private var clazz :Class<out MyViewModel>? = null
            override fun clear(viewModelClass: Class<out MyViewModel>) {
                clazz = viewModelClass
            }
        }
        viewModel = StatsViewModel(statsRepository = repository, liveDataWrapper = liveDataWrapper, clearViewModel = clearViewModel)


    }

    @Test
    fun test(){

        viewModel.update()
        liveDataWrapper.actualState(StatsUiState.Default(1,2,5))
        viewModel.newGame()
        liveDataWrapper.actualState(StatsUiState.Navigate)
        repository.actualScore()

    }


}
private interface FakeStatsRepository: StatsRepository{

    fun setScore(data:Triple<Int, Int, Int>)

    fun actualScore()

    class Base():FakeStatsRepository {

        private var score:Triple<Int, Int, Int> = Triple(0,0,0)

        override fun setScore(data: Triple<Int, Int, Int>) {
            score = data
        }

        override fun actualScore() {
            assertEquals(score,Triple(0,0,0))
        }

        override fun clear() {
            score = Triple(0,0,0)
        }

        override fun getScore(): Triple<Int, Int, Int> {
            return score
        }
    }
}
private interface FakeStatsLiveDataWrapper:StatsUiStateLiveDataWrapper.Mutable{

    fun actualState(state: StatsUiState)
    class Base:FakeStatsLiveDataWrapper{

        private var actual: StatsUiState? = null

        override fun actualState(state: StatsUiState) {
            assertEquals(state, actual)
        }

        override fun liveData(): LiveData<StatsUiState> {
            throw IllegalStateException("not use in test")
        }

        override fun update(value: StatsUiState) {
            actual = value
        }

    }
}