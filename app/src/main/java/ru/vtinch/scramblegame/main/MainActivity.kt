package ru.vtinch.scramblegame.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.vtinch.scramblegame.R
import ru.vtinch.scramblegame.core.Navigation
import ru.vtinch.scramblegame.core.Screen
import ru.vtinch.scramblegame.databinding.ActivityMainBinding
import ru.vtinch.scramblegame.di.MyViewModel
import ru.vtinch.scramblegame.di.ProvideViewModel


class MainActivity : AppCompatActivity(), Navigation, ProvideViewModel {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = (application as ProvideViewModel).viewModel(MainViewModel::class.java)
        val screen : Screen = viewModel.init(savedInstanceState == null)
        navigate(screen)
    }

    override fun navigate(screen: Screen) {
        screen.show(R.id.container,supportFragmentManager)
    }

    override fun <T : MyViewModel> viewModel(viewModelClass: Class<T>): T {
        return (application as App).viewModel(viewModelClass)
    }


}


