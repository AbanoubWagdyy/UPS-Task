package com.ups.ui.carList

import dagger.Module
import dagger.Provides
import com.ups.di.qualifiers.ViewModelInjection
import com.ups.di.InjectionViewModelProvider
import javax.inject.Singleton

@Module
class CarListModule {

    @Provides
    @ViewModelInjection
    fun provideCarListVM(
        activity: CarListActivity,
        viewModelProvider: InjectionViewModelProvider<CarListVM>
    ) = viewModelProvider.get(activity, CarListVM::class)
}