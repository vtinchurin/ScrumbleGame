package ru.vtinch.scramblegame.load.view.errorTextView

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View

class ErrorTextSavedState : View.BaseSavedState {

    private lateinit var state: ErrorTextState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel, loader: ClassLoader) : super(parcelIn, loader) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(
                ErrorTextState::class.java.classLoader,
                ErrorTextState::class.java
            ) as ErrorTextState
        } else {
            parcelIn.readSerializable() as ErrorTextState
        }
    }

    private constructor(parcelIn: Parcel) : super(parcelIn)

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore(): ErrorTextState = state

    fun save(uiState: ErrorTextState) {
        state = uiState
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.ClassLoaderCreator<ErrorTextSavedState> {
        override fun createFromParcel(source: Parcel, loader: ClassLoader): ErrorTextSavedState =
            ErrorTextSavedState(source, loader)

        override fun createFromParcel(parcel: Parcel): ErrorTextSavedState =
            ErrorTextSavedState(parcel)


        override fun newArray(size: Int): Array<ErrorTextSavedState?> =
            arrayOfNulls(size)


    }

}