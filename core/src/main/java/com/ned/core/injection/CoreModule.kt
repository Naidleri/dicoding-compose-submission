package com.ned.core.injection

import androidx.room.Room
import com.ned.core.data.local.room.CharactersDatabase
import com.ned.core.data.remote.CharactersRepository
import com.ned.core.data.remote.network.ApiConfig
import com.ned.core.domain.repository.ICharacterRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<CharactersDatabase>().charactersDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            CharactersDatabase::class.java, "Characters.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single { ApiConfig.getApiService() }
}


val repositoryModule = module {
    single<ICharacterRepository> { CharactersRepository(get(), get()) }
}
