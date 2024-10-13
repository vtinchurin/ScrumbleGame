package ru.vtinch.scramblegame.stats.view.textView


interface StatsText {

    fun update(state: StatsTextState)

    fun update(data: Triple<Int, Int, Int>)

}

