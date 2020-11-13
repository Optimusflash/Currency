package com.optimus.currency.di

import android.app.Application

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Injector.createDaggerComponent()
    }
}