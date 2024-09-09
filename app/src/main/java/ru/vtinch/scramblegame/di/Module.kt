package ru.vtinch.scramblegame.di

interface Module<T: MyViewModel> {
    fun viewModel():T
}