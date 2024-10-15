package ru.vtinch.scramblegame.stats

import ru.vtinch.scramblegame.core.cache.Cache

interface StatsRepository {

    fun clear()
    fun getScore(): Triple<Int, Int, Int>

    class Base(
        private val corrects: Cache.Mutable<Int>,
        private val skipped: Cache.Mutable<Int>,
        private val incorrect: Cache.Mutable<Int>,
        private val index: Cache.Mutable<Int>,

    ):StatsRepository{

        override fun clear() {
            corrects.save(0)
            skipped.save(0)
            incorrect.save(0)
            index.save(-1)
        }

        override fun getScore(): Triple<Int, Int, Int> {
            return Triple(corrects.restore(), incorrect.restore(), skipped.restore())
        }
    }
}