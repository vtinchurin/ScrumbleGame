package ru.vtinch.scramblegame.core

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import ru.vtinch.scramblegame.di.MyViewModel

abstract class AbstractFragment<B : ViewBinding,V : MyViewModel> : Fragment() {

    protected lateinit var viewModel : V
    protected var _binding: B? = null
    protected val binding get() = _binding!!


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}