package ru.vtinch.scramblegame.load.view.progressView


interface LoadProgress {

    fun update(state: LoadProgressState)

    fun update(visibility: Int)
}


