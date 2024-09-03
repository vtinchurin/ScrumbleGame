package ru.vtinch.scramblegame.game

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.vtinch.scramblegame.AbstractFragment
import ru.vtinch.scramblegame.App
import ru.vtinch.scramblegame.Navigate
import ru.vtinch.scramblegame.NavigateToStats
import ru.vtinch.scramblegame.databinding.FragmentGameBinding

class GameFragment : AbstractFragment<FragmentGameBinding>() {

    private lateinit var viewModel : GameViewModel

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            viewModel.handleUserInput(s.toString())
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (requireActivity().application as App).gameViewModel

        viewModel.liveData().observe(viewLifecycleOwner) {

            it.show(
                text = binding.answerText,
                userInput = binding.customInput,
                next = binding.nextButton,
                check = binding.checkButton,
                skip = binding.skipButton
            )
            it.navigate(activity as Navigate)
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