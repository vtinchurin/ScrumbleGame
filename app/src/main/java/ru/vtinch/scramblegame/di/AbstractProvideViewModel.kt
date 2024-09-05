package ru.vtinch.scramblegame.di

import androidx.lifecycle.ViewModel

abstract class AbstractProvideViewModel(
    private val core: Core,
    private val nextChain: ProvideViewModel,
    private val viewModelClass: Class<out MyViewModel>,
) : ProvideViewModel {

    override fun <T : MyViewModel> viewModel(viewModelClass: Class<T>): T {
        return if (viewModelClass == this.viewModelClass) {
            module().viewModel() as T
        } else {
            nextChain.viewModel(viewModelClass)
        }
    }

    protected abstract fun module():Module<*>
}
