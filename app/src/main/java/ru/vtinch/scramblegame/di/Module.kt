package ru.vtinch.scramblegame.di

import androidx.lifecycle.ViewModel

interface Module<T: MyViewModel> {
    fun viewModel():T
}