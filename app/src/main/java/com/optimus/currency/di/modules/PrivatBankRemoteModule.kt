package com.optimus.currency.di.modules

import com.optimus.currency.BuildConfig
import com.optimus.currency.data.remote.PrivatBankApiService
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
class PrivatBankRemoteModule {

    @Provides
    @Singleton
    @Named("PbInterceptor")
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    @Named("PbOkHttpClient")
    fun provideOkHttpClient(@Named("PbInterceptor") loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("PbRetrofit")
    fun provideRetrofit(@Named("PbOkHttpClient") client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_PRIVAT_BANK)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    @Named("PbApi")
    fun providePrivatBankApiService(@Named("PbRetrofit") retrofit: Retrofit): PrivatBankApiService{
        return retrofit.create(PrivatBankApiService::class.java)
    }

    @Provides
    @Named("PbResponseHandler")
    fun provideResponseHandler() = ResponseHandler()

}

