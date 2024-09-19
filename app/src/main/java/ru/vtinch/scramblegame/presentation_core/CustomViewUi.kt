package ru.vtinch.scramblegame.presentation_core

import java.io.Serializable

interface CustomViewUi : Serializable {
    fun <T : CustomView> update(button: T)
}