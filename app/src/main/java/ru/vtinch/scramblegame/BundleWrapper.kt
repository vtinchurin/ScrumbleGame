package ru.vtinch.scramblegame

import android.os.Build
import android.os.Bundle

interface BundleWrapper {


    interface Save: BundleWrapper{
        fun save(value: UiState)
    }

    interface Restore:BundleWrapper{
        fun restore(): UiState
    }


    interface Mutable : Save, Restore

    class Base(
        private val bundle: Bundle
    ): Mutable {
        override fun save(value: UiState) {
            bundle.putSerializable(KEY,value)
        }

        override fun restore(): UiState {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getSerializable(KEY,UiState::class.java) as UiState
            }else{
                bundle.getSerializable(KEY) as UiState
            }
        }


        companion object {
            private const val KEY = "uiState"
        }
    }

}