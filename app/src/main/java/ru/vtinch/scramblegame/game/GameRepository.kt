package ru.vtinch.scramblegame.game

import android.util.Log
import ru.vtinch.scramblegame.IntCache

interface GameRepository {

    fun getQuestion(): String
    fun getAnswer(): String
    fun next()
    fun isLast(): Boolean
    fun clear()

    fun addScore()
    fun addSkip()
    fun addIncorrect()

    class Base(
        private val index: IntCache.Mutable,
        private val corrects: IntCache.Mutable,
        private val skipped: IntCache.Mutable,
        private val incorrect: IntCache.Mutable,
        private val strategy: Strategy,
    ) : GameRepository {

        private val words = listOf("input", "world", "prediction", "snow")


        override fun getQuestion(): String {
            Log.d("tvn", strategy.getQuestion(words[index.restore()]))
            return strategy.getQuestion(words[index.restore()])
        }

        override fun getAnswer(): String {
            return words[index.restore()]
        }

        override fun next() {
            index.save(index.restore()+1)
        }

        override fun isLast() = index.restore() == words.size

        override fun clear() {
            index.save(0)
        }

        override fun addScore() {
            corrects.save(corrects.restore() + 1)
        }

        override fun addSkip() {
            skipped.save(skipped.restore() + 1)
        }

        override fun addIncorrect() {
            incorrect.save(incorrect.restore() + 1)
        }

    }

}