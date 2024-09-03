package ru.vtinch.scramblegame

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface Screen {

    fun show(containerId:Int, fragmentManager: FragmentManager)

    abstract class Replace(private val fragmentClass: Class<out Fragment>):Screen{
        override fun show(containerId: Int, fragmentManager: FragmentManager) {
            fragmentManager.beginTransaction()
                .replace(containerId,fragmentClass.getDeclaredConstructor().newInstance())
                .commit()
        }
    }
}