package ru.vtinch.scramblegame

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.vtinch.scramblegame.databinding.ActivityMainBinding

@SuppressLint("ResourceAsColor")
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel(
        liveDataWrapper = UiStateLiveDataWrapper.Base(),
        questions = QuestionLiveDataWrapper.Base()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.questions().observe(this) {
            binding.answerText.text = it.reversed()
        }

        viewModel.liveData().observe(this){
            it.show(
                skip=binding.skipButton,
                check = binding.checkButton,
                next = binding.nextButton,
                textView= binding.answerText,
                textInputLayout=binding.inputLayout,
                input = binding.inputEditText,
            )
        }

        binding.inputEditText.addTextChangedListener {
            binding.checkButton.isEnabled = it?.length == binding.answerText.length()
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
    }
}


