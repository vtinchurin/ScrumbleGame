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

    class InitialState() : UiState {
        override fun show(
            skip: Button,
            check: Button,
            next: Button,
            textView: TextView,
            textInputLayout: TextInputLayout,
            input: TextInputEditText
        ) {
            input.setText("")
            textInputLayout.visibility = View.VISIBLE
            textView.setBackgroundResource(R.drawable.bg_gray)
            skip.visibility = View.VISIBLE
            check.visibility = View.VISIBLE
            next.visibility = View.GONE
            input.clearFocus()
        }
    }

    class CorrectAnswerState() : UiState {
        override fun show(
            skip: Button,
            check: Button,
            next: Button,
            textView: TextView,
            textInputLayout: TextInputLayout,
            input: TextInputEditText
        ) {
            textView.setBackgroundResource(R.drawable.bg_green)
            textInputLayout.visibility = View.INVISIBLE
            skip.visibility = View.INVISIBLE
            check.visibility = View.GONE
            next.isEnabled = true
            next.visibility = View.VISIBLE
            input.clearFocus()
        }
    }

    class IncorrectAnswerState() : UiState {
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
            input.clearFocus()
//            input.visibility = View.VISIBLE
//            skip.visibility = View.VISIBLE
//            check.visibility = View.VISIBLE
//            next.visibility = View.GONE
        }
    }
}