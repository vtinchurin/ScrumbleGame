package ru.vtinch.scramblegame.core

import android.util.Log
import androidx.lifecycle.ViewModel

interface ViewModelFactory : ProvideViewModel, ClearViewModel {

    class Base(
        private val provideViewModel: ProvideViewModel,
    ) : ViewModelFactory {

        private val cache: MutableMap<Class<out ViewModel>, ViewModel> = mutableMapOf()

        override fun <T : ViewModel> viewModel(viewModelClass: Class<T>): T {
            if (cache.containsKey(viewModelClass)) {
                return cache[viewModelClass] as T
            } else {
                Log.d("qwe","getVM")
                val vm = provideViewModel.viewModel(viewModelClass)
                cache[viewModelClass] = vm
                return vm

            }
        }

        override fun clear(viewModelClass: Class<out ViewModel>) {
            Log.d("qwe","removeVM")
            cache.remove(viewModelClass)
        }
    }
}