package ru.vtinch.scramblegame

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import ru.vtinch.scramblegame.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(),Navigate {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState ==null)
            navigateToGame()
    }

    override fun navigate(screen: Screen) {
        screen.show(R.id.container,supportFragmentManager)
    }


}


