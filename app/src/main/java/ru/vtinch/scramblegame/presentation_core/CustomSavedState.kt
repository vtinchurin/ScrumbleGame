package ru.vtinch.scramblegame.presentation_core


import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.view.View


class CustomSavedState : View.BaseSavedState {

    private lateinit var state: CustomViewUi

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcelIn: Parcel, loader: ClassLoader) : super(parcelIn, loader) {
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readSerializable(
                CustomViewUi::class.java.classLoader,
                CustomViewUi::class.java
            ) as CustomViewUi
        } else {
            parcelIn.readSerializable() as CustomViewUi
        }
    }

    private constructor(parcelIn: Parcel) : super(parcelIn)

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore(): CustomViewUi = state

    fun save(uiState: CustomViewUi) {
        state = uiState
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.ClassLoaderCreator<CustomSavedState> {
        override fun createFromParcel(source: Parcel, loader: ClassLoader): CustomSavedState =
            CustomSavedState(source, loader)


        override fun createFromParcel(parcel: Parcel): CustomSavedState =
            CustomSavedState(parcel)


        override fun newArray(size: Int): Array<CustomSavedState?> =
            arrayOfNulls(size)
    }
}