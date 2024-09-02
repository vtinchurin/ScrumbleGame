package ru.vtinch.scramblegame

import android.util.Log
import ru.vtinch.scramblegame.game.Strategy

interface GameRepository {

    fun getQuestion(): String
    fun getAnswer(): String
    fun next()
    fun isLast(): Boolean


    fun addScore()
    fun addSkip()
    fun addIncorrect()
    fun newGame()
    fun getScore(): Triple<Int, Int, Int>

    class Base(
        private val index: IntCache.Mutable,
        private val corrects: IntCache.Mutable,
        private val skipped: IntCache.Mutable,
        private val incorrect: IntCache.Mutable,
        private val strategy: Strategy,
    ) : GameRepository {

        private var _index = index.restore()
        private val words = listOf("input", "world", "prediction", "snow")


        override fun getQuestion(): String {
            Log.d("tvn", strategy.getQuestion(words[_index]))
            return strategy.getQuestion(words[_index])
        }

        override fun getAnswer(): String {
            return words[_index]
        }

        override fun next() {
            _index++
            if (!isLast())
                index.save(_index)
        }

        override fun isLast() = _index + 1 == words.size

        override fun addScore() {
            corrects.save(corrects.restore() + 1)
        }

        override fun addSkip() {
            skipped.save(skipped.restore() + 1)
        }

        override fun addIncorrect() {
            incorrect.save(incorrect.restore() + 1)
        }

        override fun newGame() {
            index.save(0)
            corrects.save(0)
            skipped.save(0)
            incorrect.save(0)
        }

        override fun getScore(): Triple<Int, Int, Int> {
            return Triple(corrects.restore(), incorrect.restore(), skipped.restore())
        }

    }

}