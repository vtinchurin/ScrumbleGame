package ru.vtinch.scramblegame.view.button

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.button.MaterialButton


interface CustomButton {

    fun update(state: ButtonUiState)
    fun setEnable(isEnable: Boolean)
    fun setsVisibility(visibility: Int)


    abstract class AbstractButton : MaterialButton, CustomButton {

        protected lateinit var state: ButtonUiState

        constructor(context: Context) : super(context)
        constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
        constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
        )

        override fun setEnable(isEnable: Boolean) {
            this.isEnabled = isEnable
        }

        override fun update(state: ButtonUiState) {
            this.state = state
            state.update(this)
        }

        override fun setsVisibility(visibility: Int) {
            this.visibility = visibility
        }

        //override fun onSaveInstanceState(): Parcelable? {
//            return super.onSaveInstanceState()?.let {
//                    val savedState = ButtonSavedState(it)
//                    savedState.save(state)
//                    return savedState
//            }
//        }
        override fun onSaveInstanceState(): Parcelable {
            val bundle = Bundle()
            bundle.putSerializable("uiState", state)
            bundle.putParcelable("instanceState", super.onSaveInstanceState())
            return bundle
        }
        //override fun onRestoreInstanceState(state: Parcelable?) {
//            val restoredState = state as ButtonSavedState
//            super.onRestoreInstanceState(restoredState.superState)
//            update(restoredState.restore())
//        }
        override fun onRestoreInstanceState(state: Parcelable?) {
            val bundle = state as Bundle
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"))
            val restoredState = bundle.getSerializable("uiState") as ButtonUiState
            update(restoredState)
        }

    }

}
