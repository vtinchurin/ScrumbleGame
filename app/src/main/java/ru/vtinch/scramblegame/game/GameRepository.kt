package ru.vtinch.scramblegame.game

import ru.vtinch.scramblegame.core.cache.Cache
import ru.vtinch.scramblegame.load.data.local.WordDao

interface GameRepository {

    suspend fun getQuestion(): String
    fun checkPrediction(prediction: String): Boolean
    fun next()
    fun isLast(): Boolean
    fun clear()
    fun skip()

    class Base(
        private val index: Cache.Mutable<Int>,
        private val corrects: Cache.Mutable<Int>,
        private val skipped: Cache.Mutable<Int>,
        private val incorrect: Cache.Mutable<Int>,
        private val question: Cache.Mutable<String>,
        private val strategy: Strategy,
        private val dao: WordDao,
    ) : GameRepository {

        override suspend fun getQuestion(): String {
            if (question.restore() == "") {
                val word = dao.getWord(index.restore()).word
                val shuffled = strategy.getQuestion(word)
                question.save(shuffled)
            }
            return question.restore()
        }

        override fun checkPrediction(prediction: String): Boolean {
            val isCorrect = question.restore() == prediction
            if (isCorrect)
                corrects.save(corrects.restore() + 1)
            else
                incorrect.save(incorrect.restore() + 1)
            return isCorrect
        }

        override fun next() {
            index.save(index.restore() + 1)
            question.save("")
        }

        override fun isLast() = index.restore() == 10

        override fun clear() {
            //index.save(0)
        }

        override fun skip() {
            skipped.save(skipped.restore() + 1)
        }

    }

    class Test(
        private val index: Cache.Mutable<Int>,
        private val corrects: Cache.Mutable<Int>,
        private val skipped: Cache.Mutable<Int>,
        private val incorrect: Cache.Mutable<Int>,
        private val strategy: Strategy,
        private val words: List<String>,
    ) : GameRepository {

        override suspend fun getQuestion(): String {
            // Log.d("tvn", strategy.getQuestion(words[index.restore()]))
            val word = words[index.restore()]
            return strategy.getQuestion(word)
        }

        override fun checkPrediction(prediction: String): Boolean {
            val isCorrect = words[index.restore()] == prediction
            if (isCorrect)
                corrects.save(corrects.restore() + 1)
            else
                incorrect.save(incorrect.restore() + 1)
            return isCorrect
        }

        override fun next() {
            index.save(index.restore() + 1)
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