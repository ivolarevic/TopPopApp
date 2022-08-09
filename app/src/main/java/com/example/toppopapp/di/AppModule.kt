package com.example.toppopapp.di

import android.content.Context
import com.example.toppopapp.data.local.AppDatabase
import com.example.toppopapp.data.local.ArtistDao
import com.example.toppopapp.data.remote.ArtistRemoteDataSource
import com.example.toppopapp.data.remote.ArtistService
import com.example.toppopapp.data.repository.ArtistRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.deezer.com")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideArtistService(retrofit: Retrofit): ArtistService = retrofit.create(ArtistService::class.java)

    @Singleton
    @Provides
    fun provideArtistRemoteDataSource(artistService: ArtistService) = ArtistRemoteDataSource(artistService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideArtistDao(db: AppDatabase) = db.artistDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: ArtistRemoteDataSource,
                          localDataSource: ArtistDao) =
        ArtistRepository(remoteDataSource, localDataSource)
}