package ru.vtinch.scramblegame.game.view.textView

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View

class QuestionTextSavedState : View.BaseSavedState {

    private lateinit var state: QuestionTextState

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel, loader: ClassLoader) : super(parcelIn, loader) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(
                QuestionTextState::class.java.classLoader,
                QuestionTextState::class.java
            ) as QuestionTextState
        } else {
            parcelIn.readSerializable() as QuestionTextState
        }
    }

    private constructor(parcelIn: Parcel) : super(parcelIn)

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore(): QuestionTextState = state

    fun save(uiState: QuestionTextState) {
        state = uiState
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.ClassLoaderCreator<QuestionTextSavedState> {
        override fun createFromParcel(source: Parcel, loader: ClassLoader): QuestionTextSavedState =
            QuestionTextSavedState(source, loader)

        override fun createFromParcel(parcel: Parcel): QuestionTextSavedState =
            QuestionTextSavedState(parcel)


        override fun newArray(size: Int): Array<QuestionTextSavedState?> =
            arrayOfNulls(size)


    }

}