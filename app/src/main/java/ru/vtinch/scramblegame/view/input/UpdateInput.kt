package ru.vtinch.scramblegame.view.input

interface UpdateInput{

    fun update(state:InputState)
    fun update(visibility: Int)
    fun update(text:String)
    fun update(isEnabled: Boolean)

}