package ru.vtinch.scramblegame

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

interface UiState {


    fun show(
        skip: Button,
        check: Button,
        next: Button,
        textView: TextView,
        textInputLayout: TextInputLayout,
        input: TextInputEditText
    )

    object InitialState : UiState {

        override fun show(
            skip: Button,
            check: Button,
            next: Button,
            textView: TextView,
            textInputLayout: TextInputLayout,
            input: TextInputEditText
        ) {
            input.isEnabled = true
            textView.setBackgroundResource(R.drawable.bg_gray)
            input.setText("")
            textInputLayout.visibility = View.VISIBLE
            skip.visibility = View.VISIBLE
            check.visibility = View.VISIBLE
            next.visibility = View.GONE
            input.clearFocus()
        }
    }

    object CorrectAnswerState : UiState {
        override fun show(
            skip: Button,
            check: Button,
            next: Button,
            textView: TextView,
            textInputLayout: TextInputLayout,
            input: TextInputEditText
        ) {
            input.setText("")
            check.visibility = View.GONE
            textView.setBackgroundResource(R.drawable.bg_green)
            textInputLayout.visibility = View.INVISIBLE
            skip.visibility = View.INVISIBLE
            next.isEnabled = true
            next.visibility = View.VISIBLE
            input.clearFocus()
        }
    }

    object IncorrectAnswerState : UiState {
        override fun show(
            skip: Button,
            check: Button,
            next: Button,
            textView: TextView,
            textInputLayout: TextInputLayout,
            input: TextInputEditText
        ) {
            textView.setBackgroundResource(R.drawable.bg_red)
            input.setText("")
            input.isEnabled = false
            input.clearFocus()
//            input.visibility = View.VISIBLE
//            skip.visibility = View.VISIBLE
//            check.visibility = View.VISIBLE
//            next.visibility = View.GONE
        }
    }
}