package ru.vtinch.scramblegame.game.di

import ru.vtinch.scramblegame.core.RunAsync
import ru.vtinch.scramblegame.core.uiObservable.UiObservable
import ru.vtinch.scramblegame.di.Core
import ru.vtinch.scramblegame.di.Module
import ru.vtinch.scramblegame.game.GameRepository
import ru.vtinch.scramblegame.game.GameViewModel
import ru.vtinch.scramblegame.game.Strategy
import ru.vtinch.scramblegame.load.data.local.CacheDataSource

class GameModule(private val core: Core) : Module<GameViewModel> {

    override fun viewModel(): GameViewModel {

        return GameViewModel(
            gameRepository = GameRepository.Base(
                index = core.indexCache,
                corrects = core.corrects,
                incorrect = core.incorrect,
                skipped = core.skipped,
                strategy = Strategy.Game,
                cacheDataSource = CacheDataSource.Base(core.cacheModule.dao()),
                question = core.question
            ),
            clearViewModel = core.clearViewModel,
            observable = UiObservable.Base(),
            runAsync = RunAsync.Base()
        )
    }
}