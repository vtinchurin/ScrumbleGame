package ru.vtinch.scramblegame

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import ru.vtinch.scramblegame.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            viewModel.handleUserInput(s.toString())
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (application as App).viewModel
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        viewModel.liveData().observe(this) {
            it.show(
                text = binding.answerText,
                userInput = binding.customInput,
                next = binding.nextButton,
                check = binding.checkButton,
                skip = binding.skipButton
            )
        }

        binding.nextButton.setOnClickListener {
            viewModel.next()
        }

        binding.skipButton.setOnClickListener {
            viewModel.skip()
        }

        binding.checkButton.setOnClickListener {
            viewModel.check(
                binding.customInput.text()
            )
        }

        viewModel.init(savedInstanceState==null)
    }


    override fun onResume() {
        super.onResume()
        binding.customInput.addTextChangedListener(textWatcher)
    }

    override fun onPause() {
        super.onPause()
        binding.customInput.removeTextChangedListener(textWatcher)
    }


}


