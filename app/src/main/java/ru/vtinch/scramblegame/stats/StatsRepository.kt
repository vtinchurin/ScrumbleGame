package ru.vtinch.scramblegame.stats

import ru.vtinch.scramblegame.IntCache

interface StatsRepository {

    fun clear()
    fun getScore(): Triple<Int, Int, Int>

    class Base(
        private val corrects: IntCache.Mutable,
        private val skipped: IntCache.Mutable,
        private val incorrect: IntCache.Mutable,
    ):StatsRepository{

        override fun clear() {
            corrects.save(0)
            skipped.save(0)
            incorrect.save(0)
        }

        override fun getScore(): Triple<Int, Int, Int> {
            return Triple(corrects.restore(), incorrect.restore(), skipped.restore())
        }
    }
}