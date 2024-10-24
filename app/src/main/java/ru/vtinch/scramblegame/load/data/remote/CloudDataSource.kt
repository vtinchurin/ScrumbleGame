package ru.vtinch.scramblegame.load.data.remote


interface CloudDataSource {

    suspend fun getWords(): List<String>


    class Base(
        private val service: WordService,
        private val wordsCount: Int,
    ) : CloudDataSource {

        override suspend fun getWords(): List<String> {
            val response = service.load(wordsCount).execute()
            val result = response.body()!!.words
            return result
        }

    }
}
