package com.kamran.di

import com.kamran.config.AppConfig
import org.koin.core.annotation.KoinReflectAPI
import org.koin.dsl.module
import org.koin.dsl.single

@OptIn(KoinReflectAPI::class)
val appModule = module {
    // Backend Config
    single<AppConfig>()
    //single<JokeRepository> { JokeRepositoryImpl() }
}