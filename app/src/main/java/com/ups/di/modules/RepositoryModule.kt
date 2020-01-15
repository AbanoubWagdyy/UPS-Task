package com.ups.di.modules

import com.ups.data.DataRepository
import com.ups.data.RepositorySource
import com.ups.data.network.ServiceAPI
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideDataRepository(serviceAPI: ServiceAPI): RepositorySource {
        return DataRepository(serviceAPI)
    }
}