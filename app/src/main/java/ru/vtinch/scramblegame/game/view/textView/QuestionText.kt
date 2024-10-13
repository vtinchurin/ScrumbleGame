package ru.vtinch.scramblegame.game.view.textView


interface QuestionText {

    fun update(state: QuestionTextState)

    fun update(text: String)

    fun update(bgRes: Int)

}

