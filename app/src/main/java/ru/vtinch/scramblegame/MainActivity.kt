package ru.vtinch.scramblegame

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

class MainActivity : AppCompatActivity() {

    private val list = listOf("input", "world", "prediction", "fold")
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var index = 0

        binding.answerText.text = list[index].reversed()


        binding.inputEditText.addTextChangedListener {
            binding.checkButton.isEnabled = it?.length == binding.answerText.length()
        }

        binding.nextButton.setOnClickListener {
            index += 1
            binding.answerText.text = list[index].reversed()
            UiState.InitialState().show(
                binding.skipButton,
                binding.checkButton,
                binding.nextButton,
                binding.answerText,
                binding.textInputL,
                binding.inputEditText
            )
        }

        binding.skipButton.setOnClickListener {
            index += 1
            binding.answerText.text = list[index].reversed()
            UiState.InitialState().show(
                binding.skipButton,
                binding.checkButton,
                binding.nextButton,
                binding.answerText,
                binding.textInputL,
                binding.inputEditText
            )
        }

        binding.checkButton.setOnClickListener {
            if (binding.inputEditText.text.toString() == binding.answerText.text.reversed()
                    .toString()
            ) {
                UiState.CorrectAnswerState().show(
                    binding.skipButton,
                    binding.checkButton,
                    binding.nextButton,
                    binding.answerText,
                    binding.textInputL,
                    binding.inputEditText
                )
                binding.textInputL.visibility = View.INVISIBLE
            } else {
                UiState.IncorrectAnswerState().show(
                    binding.skipButton,
                    binding.checkButton,
                    binding.nextButton,
                    binding.answerText,
                    binding.textInputL,
                    binding.inputEditText
                )
                CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate).launch {
                    delay(1500)
                    UiState.InitialState().show(
                        binding.skipButton,
                        binding.checkButton,
                        binding.nextButton,
                        binding.answerText,
                        binding.textInputL,
                        binding.inputEditText
                    )
                }
            }
        }
    }
}