package ru.vtinch.scramblegame.load.view.progressView


import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View


class ProgressSavedState : View.BaseSavedState {

    private lateinit var state: LoadProgressUiState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel) : super(parcelIn) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(
                LoadProgressUiState::class.java.classLoader,
                LoadProgressUiState::class.java
            ) as LoadProgressUiState
        } else {
            parcelIn.readSerializable() as LoadProgressUiState
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore(): LoadProgressUiState = state

    fun save(uiState: LoadProgressUiState) {
        state = uiState
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<ProgressSavedState> {
        override fun createFromParcel(parcel: Parcel): ProgressSavedState =
            ProgressSavedState(parcel)

        override fun newArray(size: Int): Array<ProgressSavedState?> =
            arrayOfNulls(size)
    }
}