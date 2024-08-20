package ru.vtinch.scramblegame.view.questionTextView

interface QuestionText {

    interface SetText {
        fun setText(text: String)
    }

    interface SetBg {
        fun setBg(bgResId: Int)
    }

    interface MutableText : SetText, SetBg
}