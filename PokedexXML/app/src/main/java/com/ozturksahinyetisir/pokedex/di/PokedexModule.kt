package com.ozturksahinyetisir.pokedex.di

import android.content.Context
import com.ozturksahinyetisir.pokedex.data.remote.PokedexApi
import com.ozturksahinyetisir.pokedex.data.remote.RemoteDataSource
import com.ozturksahinyetisir.pokedex.data.remote.RemoteDataSourceImpl
import com.ozturksahinyetisir.pokedex.data.repository.PokedexRepositoryImpl
import com.ozturksahinyetisir.pokedex.domain.repository.PokedexRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PokedexModule {

    @Singleton
    @Provides
    fun provideRetrofitApi(@ApplicationContext context: Context) : PokedexApi = Retrofit.Builder()
        .client(createOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://pokeapi.co/api/v2/")
        .build().create(PokedexApi::class.java)

    private fun createOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .build()

    @Singleton
    @Provides
    fun provideRemoteDataSource(api:PokedexApi):RemoteDataSource =
        RemoteDataSourceImpl(api)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource):PokedexRepository =
        PokedexRepositoryImpl(remoteDataSource)

}