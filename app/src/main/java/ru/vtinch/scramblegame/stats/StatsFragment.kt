package ru.vtinch.scramblegame.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.vtinch.scramblegame.core.AbstractFragment
import ru.vtinch.scramblegame.core.Navigation
import ru.vtinch.scramblegame.core.uiObservable.UiObserver
import ru.vtinch.scramblegame.databinding.FragmentStatsBinding
import ru.vtinch.scramblegame.di.ProvideViewModel

class StatsFragment : AbstractFragment<FragmentStatsBinding, StatsUiState, StatsViewModel>() {

    override val update = UiObserver<StatsUiState> {
        it.show(
            text = binding.statisticsText,
            button = binding.newGameButton
        )
        it.navigate(requireActivity() as Navigation)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentStatsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (requireActivity() as ProvideViewModel).viewModel(StatsViewModel::class.java)

        viewModel.update(savedInstanceState == null)

        binding.newGameButton.setOnClickListener {
            viewModel.newGame()
        }

    }

}