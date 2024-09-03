package ru.vtinch.scramblegame.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.vtinch.scramblegame.App
import ru.vtinch.scramblegame.Navigate
import ru.vtinch.scramblegame.NavigateToGame
import ru.vtinch.scramblegame.databinding.FragmentStatsBinding
import ru.vtinch.scramblegame.AbstractFragment

class StatsFragment: AbstractFragment<FragmentStatsBinding>() {

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

        viewModel.liveData().observe(viewLifecycleOwner){

            it.show(
                binding.statisticsText,
                binding.newGameButton
            )
            it.navigate(requireActivity() as NavigateToGame)
        }

        viewModel.update()


        binding.newGameButton.setOnClickListener {
            viewModel.newGame()
        }

    }

}