package ru.vtinch.scramblegame.game.view.button


interface GameButton {

    fun update(state: GameButtonState)

    fun update(visibility: Int)

    fun update(isEnabled: Boolean)
}


