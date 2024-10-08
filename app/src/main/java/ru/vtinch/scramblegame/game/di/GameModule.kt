package ru.vtinch.scramblegame.game.di

import ru.vtinch.scramblegame.core.RunAsync
import ru.vtinch.scramblegame.di.Core
import ru.vtinch.scramblegame.di.Module
import ru.vtinch.scramblegame.game.GameRepository
import ru.vtinch.scramblegame.game.GameViewModel
import ru.vtinch.scramblegame.game.Strategy
import ru.vtinch.scramblegame.game.UiStateLiveDataWrapper

class GameModule(private val core: Core) : Module<GameViewModel> {

    override fun viewModel(): GameViewModel {

        return GameViewModel(
            gameRepository = GameRepository.Base(
                index = core.index,
                corrects = core.corrects,
                incorrect = core.incorrect,
                skipped = core.skipped,
                strategy = Strategy.Game,
                dao = core.cacheModule.dao(),
                question = core.question
            ),
            liveDataWrapper = UiStateLiveDataWrapper.Base(),
            clearViewModel = core.clearViewModel,
            runAsync = RunAsync.Base()
        )
    }
}