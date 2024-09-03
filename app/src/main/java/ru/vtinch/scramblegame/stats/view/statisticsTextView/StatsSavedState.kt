package ru.vtinch.scramblegame.stats.view.statisticsTextView


import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.os.Build


class StatsSavedState : View.BaseSavedState {

    private lateinit var state: StatsTextUiState

    constructor(superState: Parcelable) : super(superState)

      private constructor(parcelIn: Parcel) : super(parcelIn) {
       state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
             parcelIn.readSerializable(StatsTextUiState::class.java.classLoader, StatsTextUiState::class.java) as StatsTextUiState
        } else {
           parcelIn.readSerializable() as StatsTextUiState
        }
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        super.writeToParcel(out, flags)
        out.writeSerializable(state)
    }

    fun restore(): StatsTextUiState = state

    fun save(uiState: StatsTextUiState) {
        state = uiState
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<StatsSavedState> {
        override fun createFromParcel(parcel: Parcel): StatsSavedState =
            StatsSavedState(parcel)

        override fun newArray(size: Int): Array<StatsSavedState?> =
            arrayOfNulls(size)
    }
}