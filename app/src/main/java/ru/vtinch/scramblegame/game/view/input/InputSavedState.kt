package ru.vtinch.scramblegame.game.view.input

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View


class InputSavedState : View.BaseSavedState {

    private lateinit var state: InputState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(
                InputState::class.java.classLoader,
                InputState::class.java
            ) as InputState
        } else {
            parcelIn.readSerializable() as InputState
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore(): InputState = state

    fun save(uiState: InputState) {
        state = uiState
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<InputSavedState> {
        override fun createFromParcel(parcel: Parcel): InputSavedState =
            InputSavedState(parcel)

        override fun newArray(size: Int): Array<InputSavedState?> =
            arrayOfNulls(size)
    }
}
