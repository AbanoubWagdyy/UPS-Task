package com.ups.ui.carDetails

import dagger.Module
import dagger.Provides
import com.ups.di.qualifiers.ViewModelInjection
import com.ups.di.InjectionViewModelProvider

@Module
class CarDetailsModule {

    @Provides
    @ViewModelInjection
    fun provideCarDetailsVM(
        activity: CarDetailsActivity,
        viewModelProvider: InjectionViewModelProvider<CarDetailsVM>
    ) = viewModelProvider.get(activity, CarDetailsVM::class)
}