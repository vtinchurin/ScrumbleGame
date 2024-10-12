package ru.vtinch.scramblegame.load.view.button

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View

class RetryButtonSavedState : View.BaseSavedState {

    private lateinit var state: RetryButtonState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel, loader: ClassLoader) : super(parcelIn, loader) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(
                RetryButtonState::class.java.classLoader,
                RetryButtonState::class.java
            ) as RetryButtonState
        } else {
            parcelIn.readSerializable() as RetryButtonState
        }
    }

    private constructor(parcelIn: Parcel) : super(parcelIn)

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore(): RetryButtonState = state

    fun save(uiState: RetryButtonState) {
        state = uiState
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.ClassLoaderCreator<RetryButtonSavedState> {
        override fun createFromParcel(source: Parcel, loader: ClassLoader): RetryButtonSavedState =
            RetryButtonSavedState(source, loader)

        override fun createFromParcel(parcel: Parcel): RetryButtonSavedState =
            RetryButtonSavedState(parcel)


        override fun newArray(size: Int): Array<RetryButtonSavedState?> =
            arrayOfNulls(size)


    }

}