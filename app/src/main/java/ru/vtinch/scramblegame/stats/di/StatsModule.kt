package ru.vtinch.scramblegame.stats.di

import ru.vtinch.scramblegame.core.uiObservable.UiObservable
import ru.vtinch.scramblegame.di.Core
import ru.vtinch.scramblegame.di.Module
import ru.vtinch.scramblegame.stats.StatsRepository
import ru.vtinch.scramblegame.stats.StatsViewModel

class StatsModule(private val core: Core) : Module<StatsViewModel> {
    override fun viewModel(): StatsViewModel {

        return StatsViewModel(
            statsRepository = StatsRepository.Base(
                corrects = core.corrects,
                incorrect = core.incorrect,
                skipped = core.skipped,
                index = core.indexCache
            ),
            clearViewModel = core.clearViewModel,
            observable = UiObservable.Base()
        )
    }
}