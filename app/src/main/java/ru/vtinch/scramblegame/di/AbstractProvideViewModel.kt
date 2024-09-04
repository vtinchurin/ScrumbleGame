package ru.vtinch.scramblegame.di

import androidx.lifecycle.ViewModel
import ru.vtinch.scramblegame.core.ProvideViewModel

abstract class AbstractProvideViewModel(
    private val core: Core,
    private val nextChain: ProvideViewModel,
    private val viewModelClass: Class<out ViewModel>,
) : ProvideViewModel {

    override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
        return if (viewModelClass == this.viewModelClass) {
            module().viewModel() as T
        } else {
            nextChain.viewModel(viewModelClass)
        }
    }

    protected abstract fun module():Module<*>
}
