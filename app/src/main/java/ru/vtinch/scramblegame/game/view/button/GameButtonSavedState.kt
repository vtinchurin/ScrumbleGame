package ru.vtinch.scramblegame.game.view.button

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View

class GameButtonSavedState : View.BaseSavedState {

    private lateinit var state: GameButtonState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel, loader: ClassLoader) : super(parcelIn, loader) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(
                GameButtonState::class.java.classLoader,
                GameButtonState::class.java
            ) as GameButtonState
        } else {
            parcelIn.readSerializable() as GameButtonState
        }
    }

    private constructor(parcelIn: Parcel) : super(parcelIn)

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore(): GameButtonState = state

    fun save(uiState: GameButtonState) {
        state = uiState
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.ClassLoaderCreator<GameButtonSavedState> {
        override fun createFromParcel(source: Parcel, loader: ClassLoader): GameButtonSavedState =
            GameButtonSavedState(source, loader)

        override fun createFromParcel(parcel: Parcel): GameButtonSavedState =
            GameButtonSavedState(parcel)


        override fun newArray(size: Int): Array<GameButtonSavedState?> =
            arrayOfNulls(size)


    }

}