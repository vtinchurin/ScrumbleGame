package ru.vtinch.scramblegame.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.vtinch.scramblegame.App
import ru.vtinch.scramblegame.NavigateToGame
import ru.vtinch.scramblegame.databinding.FragmentStatsBinding
import ru.vtinch.scramblegame.game.view.statisticsTextView.StatsUiState

class StatsFragment:Fragment() {

    private var _binding: FragmentStatsBinding? = null

    private val binding get() = _binding!!

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
        val viewModel: StatsViewModel = (requireActivity().application as App).statsViewModel

        val (c,i,s) = viewModel.update()

        binding.statisticsText.update(StatsUiState.Base(c,i,s))

        binding.newGameButton.setOnClickListener {
            viewModel.newGame()
            (activity as NavigateToGame).navigateToGame()
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}