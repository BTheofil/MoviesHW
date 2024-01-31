package com.mbh.moviebrowser.di

import com.mbh.moviebrowser.data.network.data_source.MovieBrowserApiSource
import com.mbh.moviebrowser.data.repository.MovieBrowserRepositoryImpl
import com.mbh.moviebrowser.domain.repository.MovieBrowserRepository
import com.mbh.moviebrowser.store.MovieStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

const val BASE_URL = "https://api.themoviedb.org/3/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): MovieBrowserApiSource {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieBrowserApiSource): MovieBrowserRepository =
        MovieBrowserRepositoryImpl(apiSource = api)

    @Provides
    @Singleton
    fun provideMovieStore(repository: MovieBrowserRepository): MovieStore = MovieStore(repository)
}