package ru.vtinch.scramblegame.view.button


import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View


class ButtonSavedState : View.BaseSavedState {

    private lateinit var state: ButtonUiState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel, loader: ClassLoader) : super(parcelIn, loader) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(
                ButtonUiState::class.java.classLoader,
                ButtonUiState::class.java
            ) as ButtonUiState
        } else {
            parcelIn.readSerializable() as ButtonUiState
        }
    }

    private constructor(parcelIn: Parcel) : super(parcelIn)

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore(): ButtonUiState = state

    fun save(uiState: ButtonUiState) {
        state = uiState
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.ClassLoaderCreator<ButtonSavedState> {
        override fun createFromParcel(parcel: Parcel, loader: ClassLoader): ButtonSavedState =
            ButtonSavedState(parcel, loader)

        override fun createFromParcel(parcel: Parcel): ButtonSavedState =
            ButtonSavedState(parcel)

        override fun newArray(size: Int): Array<ButtonSavedState?> =
            arrayOfNulls(size)
    }
}