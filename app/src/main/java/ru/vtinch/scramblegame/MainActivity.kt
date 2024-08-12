package ru.vtinch.scramblegame

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.observeOn
import ru.vtinch.scramblegame.databinding.ActivityMainBinding
import kotlin.coroutines.CoroutineContext

@SuppressLint("ResourceAsColor")
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel(repository = Repository.Base(), liveDataWrapper = UiStateLiveDataWrapper.Base())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val flow = viewModel._uiState

        flow.value.show(binding)
//        viewModel.liveData().observe(this){
//            it.show(binding)
//        }

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


