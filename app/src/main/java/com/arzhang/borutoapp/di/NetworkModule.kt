package com.arzhang.borutoapp.di

import com.arzhang.borutoapp.data.local.BorutoDatabase
import com.arzhang.borutoapp.data.remote.BorutoApi
import com.arzhang.borutoapp.data.repository.RemoteDataSourceImpl
import com.arzhang.borutoapp.domain.repository.RemoteDataSource
import com.arzhang.borutoapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient) : Retrofit {
        val contentType = MediaType.get("application/json")
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun providesBorutoApi(retrofit: Retrofit) : BorutoApi {
        return retrofit.create(BorutoApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(borutoApi: BorutoApi, borutoDatabase: BorutoDatabase) : RemoteDataSource {
        return RemoteDataSourceImpl(borutoApi = borutoApi, borutoDatabase = borutoDatabase)
    }


}