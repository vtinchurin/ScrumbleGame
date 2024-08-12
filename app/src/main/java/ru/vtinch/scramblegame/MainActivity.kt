package ru.vtinch.scramblegame

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import ru.vtinch.scramblegame.databinding.ActivityMainBinding

@SuppressLint("ResourceAsColor")
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel(
        repository = Repository.Base(),
        liveDataWrapper = UiStateLiveDataWrapper.Base()
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.liveData().observe(this) {
            it.show(binding)
        }

        binding.inputEditText.addTextChangedListener {
            viewModel.handleUserInput(it.toString())
        }

        binding.nextButton.setOnClickListener {
            viewModel.next()
        }

        binding.skipButton.setOnClickListener {
            viewModel.skip()
        }

        binding.checkButton.setOnClickListener {
            viewModel.check(
                binding.inputEditText.text.toString()
            )
        }
        viewModel.init()
    }
}


