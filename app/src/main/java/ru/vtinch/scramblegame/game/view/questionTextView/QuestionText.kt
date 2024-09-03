package ru.vtinch.scramblegame.game.view.questionTextView

interface QuestionText {
    fun update(state: TextUiState)
    fun setText(text: String)
    fun setBg(bgResId: Int)
}