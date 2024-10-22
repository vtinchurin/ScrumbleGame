package ru.vtinch.scramblegame.load.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.vtinch.scramblegame.core.RunAsync
import ru.vtinch.scramblegame.core.uiObservable.UiObservable
import ru.vtinch.scramblegame.di.Core
import ru.vtinch.scramblegame.di.Module
import ru.vtinch.scramblegame.load.LoadRepository
import ru.vtinch.scramblegame.load.LoadViewModel
import ru.vtinch.scramblegame.load.data.local.CacheDataSource
import ru.vtinch.scramblegame.load.data.remote.CloudDataSource
import ru.vtinch.scramblegame.load.data.remote.HandleError
import ru.vtinch.scramblegame.load.data.remote.WordService

class LoadModule(
    private val core: Core
) : Module<LoadViewModel> {
    override fun viewModel(): LoadViewModel {

        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()

        val retrofit:Retrofit = Retrofit.Builder()
            .baseUrl("https://ao0ixd.buildship.run/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val service = retrofit.create(WordService::class.java)

        return LoadViewModel(
            repository = LoadRepository.Base(
                cloudDataSource = CloudDataSource.Base(service, core.wordsCount),
                cacheDataSource = CacheDataSource.Base(core.cacheModule.dao()),
                index = core.indexCache,
                handleError = HandleError.Data()
            ),
            runAsync = RunAsync.Base(),
            observable = UiObservable.Base(),
            clearViewModel = core.clearViewModel,
            handleError = HandleError.Ui()
        )
    }
}

