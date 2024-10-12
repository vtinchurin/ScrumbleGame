package ru.vtinch.scramblegame.stats.view.textView

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View

class StatsTextSavedState : View.BaseSavedState {

    private lateinit var state: StatsTextState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel, loader: ClassLoader) : super(parcelIn, loader) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(
                StatsTextState::class.java.classLoader,
                StatsTextState::class.java
            ) as StatsTextState
        } else {
            parcelIn.readSerializable() as StatsTextState
        }
    }

    private constructor(parcelIn: Parcel) : super(parcelIn)

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore(): StatsTextState = state

    fun save(uiState: StatsTextState) {
        state = uiState
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.ClassLoaderCreator<StatsTextSavedState> {
        override fun createFromParcel(source: Parcel, loader: ClassLoader): StatsTextSavedState =
            StatsTextSavedState(source, loader)

        override fun createFromParcel(parcel: Parcel): StatsTextSavedState =
            StatsTextSavedState(parcel)


        override fun newArray(size: Int): Array<StatsTextSavedState?> =
            arrayOfNulls(size)


    }

}