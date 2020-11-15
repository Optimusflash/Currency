package com.optimus.currency.di

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */
object Injector {
    private lateinit var appComponent: AppComponent

    fun createDaggerComponent(){
        appComponent = DaggerAppComponent.create()
    }

    fun getAppComponent() = appComponent
}