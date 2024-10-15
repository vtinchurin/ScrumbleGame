package ru.vtinch.scramblegame.game

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.vtinch.scramblegame.core.AbstractFragment
import ru.vtinch.scramblegame.core.Navigation
import ru.vtinch.scramblegame.core.uiObservable.UiObserver
import ru.vtinch.scramblegame.databinding.FragmentGameBinding
import ru.vtinch.scramblegame.di.ProvideViewModel

class GameFragment : AbstractFragment<FragmentGameBinding, GameUiState, GameViewModel>() {

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        override fun afterTextChanged(s: Editable?) {
            viewModel.handleUserInput(s.toString())
        }

    }

    override val update = UiObserver<GameUiState> { uiState ->
        uiState.navigate(requireActivity() as Navigation)
        uiState.show(
            text = binding.answerText,
            userInput = binding.customInput,
            next = binding.nextButton,
            check = binding.checkButton,
            skip = binding.skipButton
        )
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

        viewModel = (requireActivity().application as ProvideViewModel).viewModel(GameViewModel::class.java)

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