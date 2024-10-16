package ru.vtinch.scramblegame.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import ru.vtinch.scramblegame.core.uiObservable.UiObserver
import ru.vtinch.scramblegame.di.MyViewModel
import ru.vtinch.scramblegame.di.UiState

abstract class AbstractFragment<binding : ViewBinding, uiState : UiState, viewModel : MyViewModel.Abstract<uiState>> :
    Fragment() {

    protected abstract val update: UiObserver<uiState>
    protected lateinit var viewModel: viewModel
    private var _binding: binding? = null
    protected val binding get() = _binding!!

    protected abstract fun inflate(inflater: LayoutInflater, container: ViewGroup?): binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = inflate(inflater, container)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.startUpdate(update)
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopUpdate()
    }
}
