package com.optimus.currency.di.modules

import androidx.lifecycle.ViewModel
import com.optimus.currency.di.ViewModelKey
import com.optimus.currency.ui.SharedViewModel
import com.optimus.currency.ui.nbu.viewmodel.NBUViewModel
import com.optimus.currency.ui.privatbank.viewmodel.PrivateBankViewModel
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
    @ViewModelKey(SharedViewModel::class)
    abstract fun provideSharedViewModel(sharedViewModel: SharedViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(PrivateBankViewModel::class)
    abstract fun providePrivateBankViewModel(privateBankViewModel: PrivateBankViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(NBUViewModel::class)
    abstract fun provideNBUViewModel(nbuViewModel: NBUViewModel): ViewModel
}