package ru.vtinch.scramblegame.stats

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import ru.vtinch.scramblegame.core.Cache

class StatsRepositoryTest {
    private lateinit var repository: StatsRepository
    private lateinit var corrects: FakeIntCache
    private lateinit var incorrects: FakeIntCache
    private lateinit var skipped: FakeIntCache

    @Before
    fun setup() {
        corrects = FakeIntCache.Base()
        incorrects = FakeIntCache.Base()
        skipped = FakeIntCache.Base()
        repository = StatsRepository.Base(
            corrects = corrects,
            incorrect = incorrects,
            skipped = skipped,
        )

        corrects.save(1)
        incorrects.save(1)
        skipped.save(4)
    }

    @Test
    fun readScore() {

        assertEquals(Triple(1, 1, 4), repository.getScore())

    }

    @Test
    fun clearScore() {
        repository.clear()

        corrects.assertValue(0)
        incorrects.assertValue(0)
        skipped.assertValue(0)
    }
}


private interface FakeIntCache : Cache.Mutable<Int> {

    fun assertValue(value: Int)

    class Base : FakeIntCache {

        private var current = 0

        override fun assertValue(value: Int) {
            assertEquals(current, value)
        }

        override fun save(value: Int) {
            current = value
        }

        override fun restore(): Int {
            return current
        }
    }
}