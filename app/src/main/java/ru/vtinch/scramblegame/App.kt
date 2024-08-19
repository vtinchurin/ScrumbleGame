package ru.vtinch.scramblegame

import android.app.Application

class App : Application() {

    lateinit var viewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        val sharedPrefs = getSharedPreferences("UnscrambleApp", MODE_PRIVATE)

        viewModel = MainViewModel(
            repository = Repository.Base(IntCache.Base(key = "index", sharedPrefs)),
            liveDataWrapper = UiStateLiveDataWrapper.Base()
        )
    }
}