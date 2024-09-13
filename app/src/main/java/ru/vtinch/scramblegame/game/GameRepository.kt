package ru.vtinch.scramblegame.game

import android.util.Log
import ru.vtinch.scramblegame.core.IntCache

interface GameRepository {

    fun getQuestion(): String
    fun checkPrediction(prediction:String): Boolean
    fun next()
    fun isLast(): Boolean
    fun clear()
    fun skip()


    class Base(
        private val index: IntCache.Mutable,
        private val corrects: IntCache.Mutable,
        private val skipped: IntCache.Mutable,
        private val incorrect: IntCache.Mutable,
        private val strategy: Strategy,
    ) : GameRepository {

        private val words = listOf("input", "world", "prediction", "snow")


        override fun getQuestion(): String {
           // Log.d("tvn", strategy.getQuestion(words[index.restore()]))
            return strategy.getQuestion(words[index.restore()])
        }

        override fun checkPrediction(prediction: String): Boolean {
            val isCorrect = words[index.restore()] == prediction
            if(isCorrect)
                corrects.save(corrects.restore()+1)
            else
                incorrect.save(incorrect.restore()+1)
            return isCorrect
        }

        override fun next() {
            index.save(index.restore()+1)
        }

        override fun isLast() = index.restore() == words.size

        override fun clear() {
            index.save(0)
        }

        override fun skip() {
            skipped.save(skipped.restore() + 1)
        }

    }

}