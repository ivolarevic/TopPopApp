package com.example.toppopapp.di

import android.content.Context
import com.example.toppopapp.data.local.*
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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient) : Retrofit = Retrofit.Builder()
            // base url
            //build config
        .baseUrl("https://api.deezer.com")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
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
    fun provideAlbumDao(db: AppDatabase) = db.albumDao()

    @Singleton
    @Provides
    fun provideSongDao(db: AppDatabase) = db.songDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: ArtistRemoteDataSource,
                          songDao: SongDao, artistDao: ArtistDao, albumDao: AlbumDao) =
        ArtistRepository(remoteDataSource, songDao, artistDao, albumDao)
}