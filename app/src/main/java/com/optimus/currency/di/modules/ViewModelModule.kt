package com.optimus.currency.di.modules

import androidx.lifecycle.ViewModel
import com.optimus.currency.di.ViewModelKey
import com.optimus.currency.ui.privatbank.viewmodel.PrivatBankViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
@Module
abstract class ViewModelModule {
    @IntoMap
    @Binds
    @ViewModelKey(PrivatBankViewModel::class)
    abstract fun providePrivatBankViewModel(mainViewModel: PrivatBankViewModel): ViewModel
}