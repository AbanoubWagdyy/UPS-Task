package com.ups.di.modules

import com.ups.ui.carDetails.CarDetailsActivity
import com.ups.ui.carDetails.CarDetailsModule
import com.ups.ui.carList.CarListActivity
import com.ups.ui.carList.CarListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjectorsModule {

    @ContributesAndroidInjector(modules = [CarListModule::class])
    abstract fun carListActivityInjector(): CarListActivity

    @ContributesAndroidInjector(modules = [CarDetailsModule::class])
    abstract fun carDetailsActivityInjector(): CarDetailsActivity
}