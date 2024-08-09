package ru.vtinch.scramblegame.data

import ru.vtinch.scramblegame.domain.Repository

class RepositoryImpl(
    private val dataSource: DataSource
): Repository {


    override fun nextWord(): String{
        return dataSource.getData()
    }
}