package ru.vtinch.scramblegame.di

import android.util.Log

interface ManageViewModels : ProvideViewModel, ClearViewModel {

    class Factory(
        private val provideViewModel: ProvideViewModel,
    ) : ManageViewModels {

        private val cache: MutableMap<Class<out MyViewModel>, MyViewModel> = mutableMapOf()

        override fun <T : MyViewModel> viewModel(viewModelClass: Class<T>): T {
            if (cache.containsKey(viewModelClass)) {
                return cache[viewModelClass] as T
            } else {
                Log.d("qwe","getVM")
                val vm = provideViewModel.viewModel(viewModelClass)
                cache[viewModelClass] = vm
                return vm

            }
        }

        override fun clear(viewModelClass: Class<out MyViewModel>) {
            Log.d("qwe","removeVM")
            cache.remove(viewModelClass)
        }
    }
}