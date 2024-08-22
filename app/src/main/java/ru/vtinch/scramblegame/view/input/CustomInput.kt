package ru.vtinch.scramblegame.view.input

import android.content.Context
import android.os.Parcelable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import ru.vtinch.scramblegame.databinding.InputviewlayoutBinding

class CustomInput : FrameLayout,UpdateInput{

    private lateinit var state:InputState

    private val binding = InputviewlayoutBinding.inflate(LayoutInflater.from(this.context),this,true)
    constructor(context: Context):super(context)
    constructor(context: Context,attrs:AttributeSet):super(context,attrs)
    constructor(context: Context,attrs:AttributeSet,defStyleAttr: Int):super(context,attrs,defStyleAttr)

    override fun update(state: InputState) {
        this.state = state
        state.update(this)
    }

    override fun update(visibility: Int) {
        binding.inputLayout.visibility = visibility
    }

    override fun update(text: String) {
        binding.inputEditText.setText(text)
    }

    override fun update(isEnabled: Boolean) {
        binding.inputEditText.isEnabled = isEnabled
    }

    fun text():String{
        return binding.inputEditText.text.toString()
    }

    fun addTextChangedListener(textWatcher: TextWatcher){
        binding.inputEditText.addTextChangedListener(textWatcher)
    }
    fun removeTextChangedListener(textWatcher: TextWatcher){
        binding.inputEditText.removeTextChangedListener(textWatcher)
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = InputSavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as InputSavedState
        super.onRestoreInstanceState(restoredState.superState)
        update(restoredState.restore())
    }
}

