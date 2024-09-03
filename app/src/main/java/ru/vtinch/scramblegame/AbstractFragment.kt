package ru.vtinch.scramblegame

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class AbstractFragment<T: ViewBinding>: Fragment() {

    protected var _binding: T? = null
    protected val binding get() = _binding!!


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}