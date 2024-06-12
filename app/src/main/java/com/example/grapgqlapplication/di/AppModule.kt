package com.example.grapgqlapplication.di

import com.apollographql.apollo3.ApolloClient
import com.example.grapgqlapplication.data.repository.ApolloPokemonClient
import com.example.grapgqlapplication.data.repository.PokemonClient
import com.example.grapgqlapplication.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideApolloPokemonClient(client:ApolloClient): PokemonClient {
        return ApolloPokemonClient(client)
    }
}