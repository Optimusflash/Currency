package com.optimus.currency.di

import com.optimus.currency.di.modules.NBURemoteModule
import com.optimus.currency.di.modules.PrivatBankRemoteModule
import com.optimus.currency.di.modules.ViewModelModule
import com.optimus.currency.ui.privatbank.main.MainActivity
import com.optimus.currency.ui.nbu.fragments.NBUFragment
import com.optimus.currency.ui.privatbank.fragments.PrivatBankFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */

@Singleton
@Component(modules = [PrivatBankRemoteModule::class, ViewModelModule::class, NBURemoteModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(fragment: PrivatBankFragment)
    fun inject(fragment: NBUFragment)
}