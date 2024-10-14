package ru.vtinch.scramblegame.stats

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.vtinch.scramblegame.di.ClearViewModel
import ru.vtinch.scramblegame.di.MyViewModel
import ru.vtinch.scramblegame.game.FakeObservable

class StatsViewModelTest {

    private lateinit var repository: FakeStatsRepository
    private lateinit var observable: FakeObservable<StatsUiState>
    private lateinit var viewModel: StatsViewModel
    private lateinit var clearViewModel: ClearViewModel

    @Before
    fun setup() {

        repository = FakeStatsRepository.Base()
        repository.setScore(Triple(1,2,5))
        observable = FakeObservable.Base()
        clearViewModel = object : ClearViewModel{
            private var clazz :Class<out MyViewModel>? = null
            override fun clear(viewModelClass: Class<out MyViewModel>) {
                clazz = viewModelClass
            }
        }
        viewModel = StatsViewModel(
            statsRepository = repository,
            observable = observable, clearViewModel = clearViewModel
        )


    }

    @Test
    fun test(){

        viewModel.update()
        observable.assertState(StatsUiState.Default(1, 2, 5))
        viewModel.newGame()
        observable.assertState(StatsUiState.Navigate)
        repository.actualScore()

    }


}
private interface FakeStatsRepository: StatsRepository{

    fun setScore(data:Triple<Int, Int, Int>)

    fun actualScore()

    class Base() : FakeStatsRepository {

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