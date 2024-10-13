package ru.vtinch.scramblegame.load.view.errorTextView


interface ErrorText {

    fun update(state: ErrorTextState)

    fun update(text: String)

    fun update(visibility: Int)

    fun setTextRes(textResId: Int)

}


