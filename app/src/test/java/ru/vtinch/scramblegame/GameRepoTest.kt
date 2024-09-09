package ru.vtinch.scramblegame

import android.content.SharedPreferences
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import ru.vtinch.scramblegame.core.IntCache
import ru.vtinch.scramblegame.game.GameRepository
import ru.vtinch.scramblegame.game.Strategy

class GameRepoTest{

    private lateinit var repository: GameRepository
    private lateinit var correct: FakeCache
    private lateinit var skipped: FakeCache
    private lateinit var incorrect: FakeCache
    private lateinit var index: FakeCache
    private lateinit var strategy: Strategy


    @Before
    fun setup(){

        correct = FakeCache.Base()
        skipped = FakeCache.Base()
        incorrect = FakeCache.Base()
        index = FakeCache.Base()
        strategy = Strategy.Test()
        repository = GameRepository.Base(
            index = index,
            corrects = correct,
            incorrect = incorrect,
            skipped = skipped,
            strategy = strategy
        )

    }
    @Test
    fun test(){

        var question = repository.getQuestion()
        index.assertValue(0)
        assertEquals("input".reversed(),question)
        repository.checkPrediction("input")
        correct.assertValue(1)
        repository.next()
        index.assertValue(1)
        question = repository.getQuestion()
        assertEquals("world".reversed(),question)
        repository.checkPrediction("wordl")
        incorrect.assertValue(1)
        repository.skip()
        skipped.assertValue(1)

        assertEquals(false,repository.isLast())
    }

    @Test
    fun clearRepo(){
        repository.clear()
        index.assertValue(0)
    }

    @Test
    fun notLast(){
        assertEquals(false,repository.isLast())
    }
    @Test
    fun isLast(){
        repeat(7){
            repository.skip()
        }
        assertEquals(true,repository.isLast())
    }

}

private interface FakeCache:IntCache.Mutable{

    fun assertValue(value:Int)

    class Base:FakeCache {

        private var current = 0

        override fun assertValue(value: Int) {
            assertEquals(current,value)
        }

        override fun save(index: Int) {
            current=index
        }

        override fun restore(): Int {
            return current
        }
    }
}
