package ru.vtinch.scramblegame.load.data.local

interface CacheDataSource {

    interface Save {
        suspend fun save(words: List<String>)
    }

    interface Read {
        suspend fun word(index: Int): String
    }

    interface Mutable : Save, Read

    class Base(
        private val dao: WordDao,
    ) : Mutable {
        override suspend fun save(words: List<String>) {
            dao.addWords(words.mapIndexed { index, word ->
                WordCache(index, word)
            })
        }

        override suspend fun word(index: Int): String {
            return dao.getWord(index).word
        }
    }
}