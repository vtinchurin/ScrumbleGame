package ru.vtinch.scramblegame.di

import androidx.lifecycle.ViewModel

interface Module<T: ViewModel> {
    fun viewModel():T
}