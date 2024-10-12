package ru.vtinch.scramblegame.load.view.progressView

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View

class LoadProgressSavedState : View.BaseSavedState {

    private lateinit var state: LoadProgressState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel, loader: ClassLoader) : super(parcelIn, loader) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(
                LoadProgressState::class.java.classLoader,
                LoadProgressState::class.java
            ) as LoadProgressState
        } else {
            parcelIn.readSerializable() as LoadProgressState
        }
    }

    private constructor(parcelIn: Parcel) : super(parcelIn)

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore(): LoadProgressState = state

    fun save(uiState: LoadProgressState) {
        state = uiState
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.ClassLoaderCreator<LoadProgressSavedState> {
        override fun createFromParcel(source: Parcel, loader: ClassLoader): LoadProgressSavedState =
            LoadProgressSavedState(source, loader)

        override fun createFromParcel(parcel: Parcel): LoadProgressSavedState =
            LoadProgressSavedState(parcel)


        override fun newArray(size: Int): Array<LoadProgressSavedState?> =
            arrayOfNulls(size)


    }

}