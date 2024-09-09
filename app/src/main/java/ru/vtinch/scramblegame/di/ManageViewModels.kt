package ru.vtinch.scramblegame.di

interface ManageViewModels : ProvideViewModel, ClearViewModel {

    class Factory(
        private val provideViewModel: ProvideViewModel,
    ) : ManageViewModels {

        private val cache: MutableMap<Class<out MyViewModel>, MyViewModel> = mutableMapOf()

        override fun <T : MyViewModel> viewModel(viewModelClass: Class<T>): T {
            if (cache.containsKey(viewModelClass)) {
                return cache[viewModelClass] as T
            } else {
                val vm = provideViewModel.viewModel(viewModelClass)
                cache[viewModelClass] = vm
                return vm

            }
        }

        override fun clear(viewModelClass: Class<out MyViewModel>) {
            cache.remove(viewModelClass)
        }
    }
}