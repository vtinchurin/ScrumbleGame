package ru.vtinch.scramblegame.game

import ru.vtinch.scramblegame.core.cache.Cache

interface GameRepository {

    fun getQuestion(): String
    fun checkPrediction(prediction:String): Boolean
    fun next()
    fun isLast(): Boolean
    fun clear()
    fun skip()


    class Base(
        private val index: Cache.Mutable<Int>,
        private val corrects: Cache.Mutable<Int>,
        private val skipped: Cache.Mutable<Int>,
        private val incorrect: Cache.Mutable<Int>,
        private val strategy: Strategy,
        private val words: List<String>,
    ) : GameRepository {

        constructor(
            index: Cache.Mutable<Int>,
            corrects: Cache.Mutable<Int>,
            skipped: Cache.Mutable<Int>,
            incorrect: Cache.Mutable<Int>,
            dataCache: Cache.Mutable<Set<String>>,
            strategy: Strategy,
        ) : this(
            index,
            corrects,
            skipped,
            incorrect,
            strategy,
            words = dataCache.restore().toList()
        )

        //private val words = listOf("input", "world", "prediction", "snow")


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