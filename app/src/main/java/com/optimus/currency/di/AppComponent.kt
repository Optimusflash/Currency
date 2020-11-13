package com.optimus.currency.di

import com.optimus.currency.di.modules.RemoteModule
import com.optimus.currency.di.modules.ViewModelModule
import com.optimus.currency.ui.MainActivity
import com.optimus.currency.ui.privatbank.fragments.PrivatBankFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */

@Singleton
@Component(modules = [RemoteModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(fragment: PrivatBankFragment)
}