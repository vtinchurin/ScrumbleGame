package ru.vtinch.scramblegame.game.di

import ru.vtinch.scramblegame.core.RunAsync
import ru.vtinch.scramblegame.di.Core
import ru.vtinch.scramblegame.di.Module
import ru.vtinch.scramblegame.game.GameRepository
import ru.vtinch.scramblegame.game.GameViewModel
import ru.vtinch.scramblegame.game.Strategy
import ru.vtinch.scramblegame.game.UiStateLiveDataWrapper

class TestGameModule(private val core: Core) : Module<GameViewModel> {

    override fun viewModel(): GameViewModel {

        return GameViewModel(
            gameRepository = GameRepository.Test(
                index = core.index,
                corrects = core.corrects,
                incorrect = core.incorrect,
                skipped = core.skipped,
                words = listOf("input", "world", "prediction", "snow"),
                strategy = Strategy.Test

            ),
            liveDataWrapper = UiStateLiveDataWrapper.Base(),
            clearViewModel = core.clearViewModel,
            runAsync = RunAsync.Base()
        )
    }
}