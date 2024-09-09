package ru.vtinch.scramblegame.di

abstract class AbstractProvideViewModel(
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
    protected abstract fun module():Module<out MyViewModel>
}