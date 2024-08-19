package ru.vtinch.scramblegame

import android.util.Log


interface UiStateLiveDataWrapper {

    interface Read: LiveDataWrapper.Read<UiState>
    interface Update: LiveDataWrapper.Update<UiState>
    interface Save:LiveDataWrapper{
        fun save(bundleWrapper: BundleWrapper.Save)
    }
    interface Mutable : Read, Update, Save


    class Base: Mutable, LiveDataWrapper.Abstract<UiState>(){

        override fun save(bundleWrapper: BundleWrapper.Save) {
            liveData.value?.let { bundleWrapper.save(it) }
        }
    }

}