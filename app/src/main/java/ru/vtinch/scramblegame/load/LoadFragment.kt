package ru.vtinch.scramblegame.load

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.vtinch.scramblegame.core.AbstractFragment
import ru.vtinch.scramblegame.core.Navigation
import ru.vtinch.scramblegame.core.uiObservable.UiObserver
import ru.vtinch.scramblegame.databinding.FragmentLoadBinding
import ru.vtinch.scramblegame.di.ProvideViewModel

class LoadFragment : AbstractFragment<FragmentLoadBinding, LoadViewModel>() {

    private val update = object : UiObserver<LoadUiState> {
        override fun updateUi(data: LoadUiState) {
            data.show(
                binding.errorText,
                binding.retryButton,
                binding.progressUi
            )
            data.navigate(requireActivity() as Navigation)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoadBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (requireActivity() as ProvideViewModel).viewModel(LoadViewModel::class.java)


        binding.retryButton.setOnClickListener {
            viewModel.load()
        }

        viewModel.load(isFirstRun = savedInstanceState == null)
    }

    override fun onResume() {
        super.onResume()
        viewModel.startUpdate(observer = update)
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopUpdate()
    }
}