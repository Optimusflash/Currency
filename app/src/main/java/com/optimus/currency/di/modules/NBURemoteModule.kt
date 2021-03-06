package com.optimus.currency.di.modules

import com.optimus.currency.BuildConfig
import com.optimus.currency.data.remote.NBUApiService
import com.optimus.currency.utils.ResponseHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Dmitriy Chebotar on 13.11.2020.
 */

@Module
@InstallIn(ApplicationComponent::class)
class NBURemoteModule {

    @Provides
    @Singleton
    @Named("NBUInterceptor")
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    @Named("NBUOkHttpClient")
    fun provideOkHttpClient(@Named("NBUInterceptor") loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("NBURetrofit")
    fun provideRetrofit(@Named("NBUOkHttpClient") client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_NBU)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    @Named("NBUApi")
    fun provideNbuApiService(@Named("NBURetrofit") retrofit: Retrofit): NBUApiService {
        return retrofit.create(NBUApiService::class.java)
    }

    @Provides
    @Named("NBUResponseHandler")
    fun provideResponseHandler() = ResponseHandler()

}