package com.learningwithmanos.uniexercise.heroes.di

import android.content.Context
import android.content.SharedPreferences
import com.learningwithmanos.uniexercise.heroes.api.request.MarvelApi
import com.learningwithmanos.uniexercise.heroes.repo.HeroRepository
import com.learningwithmanos.uniexercise.heroes.repo.HeroRepositoryImpl
import com.learningwithmanos.uniexercise.heroes.repo.UserKeysRepository
import com.learningwithmanos.uniexercise.heroes.repo.UserKeysRepositoryImpl
import com.learningwithmanos.uniexercise.heroes.source.local.DBWrapper
import com.learningwithmanos.uniexercise.heroes.source.local.DummyDBWrapper
import com.learningwithmanos.uniexercise.heroes.source.local.HeroLocalSource
import com.learningwithmanos.uniexercise.heroes.source.local.HeroLocalSourceImpl
import com.learningwithmanos.uniexercise.heroes.source.remote.DummyRestFrameworkWrapper
import com.learningwithmanos.uniexercise.heroes.source.remote.HeroRemoteSource
import com.learningwithmanos.uniexercise.heroes.source.remote.HeroRemoteSourceImpl
import com.learningwithmanos.uniexercise.heroes.source.remote.RestFrameworkWrapper
import com.learningwithmanos.uniexercise.heroes.usecase.ErrorHandling
import com.learningwithmanos.uniexercise.heroes.usecase.ErrorHandlingImpl
import com.learningwithmanos.uniexercise.heroes.usecase.GetHeroesSortedByHighestNumberOfComicsUC
import com.learningwithmanos.uniexercise.heroes.usecase.GetHeroesSortedByHighestNumberOfComicsUCImpl
import com.learningwithmanos.uniexercise.heroes.usecase.GetHeroesSortedByNameUC
import com.learningwithmanos.uniexercise.heroes.usecase.GetHeroesSortedByNameUCImpl
import com.learningwithmanos.uniexercise.heroes.usecase.GetHeroesUC
import com.learningwithmanos.uniexercise.heroes.usecase.GetHeroesUCImpl
import com.learningwithmanos.uniexercise.heroes.usecase.GetUserApiKeys
import com.learningwithmanos.uniexercise.heroes.usecase.GetUserApiKeysImpl
import com.learningwithmanos.uniexercise.heroes.usecase.KeysChanged
import com.learningwithmanos.uniexercise.heroes.usecase.KeysChangedImpl
import com.learningwithmanos.uniexercise.heroes.usecase.SaveUserApiKeys
import com.learningwithmanos.uniexercise.heroes.usecase.SaveUserApiKeysImpl
import com.learningwithmanos.uniexercise.heroes.utils.sharedpreferences.MyPreferences
import com.learningwithmanos.uniexercise.heroes.vm.heroes.HeroesViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface HeroesModule {

    // Usecase

    @Binds
    fun bindsGetHeroesUC(
        getHeroesUCImpl: GetHeroesUCImpl
    ): GetHeroesUC

    @Binds
    fun bindsGetHeroesSortedByNameUC(
        getHeroesSortedByNameUCImpl: GetHeroesSortedByNameUCImpl
    ): GetHeroesSortedByNameUC

    @Binds
    fun bindsGetHeroesSortedByHighestNumberOfComicsUC(
        getHeroesSortedByHighestNumberOfComicsUCImpl: GetHeroesSortedByHighestNumberOfComicsUCImpl
    ): GetHeroesSortedByHighestNumberOfComicsUC

    // Repository

    @Binds
    fun bindsHeroRepository(
        heroRepositoryImpl: HeroRepositoryImpl
    ): HeroRepository

    // Source

    @Binds
    @Singleton
    fun bindsHeroLocalSource(
        heroLocalSourceImpl: HeroLocalSourceImpl
    ): HeroLocalSource

    @Binds
    @Singleton
    fun bindsHeroRemoteSource(
        heroRemoteSourceImpl: HeroRemoteSourceImpl
    ): HeroRemoteSource

    // external frameworks

    @Binds
    @Singleton
    fun bindsRestFrameworkWrapper(
        dummyRestFrameworkWrapper: DummyRestFrameworkWrapper
    ): RestFrameworkWrapper

    @Binds
    @Singleton
    fun bindsDBWrapper(
        dummyDBWrapper: DummyDBWrapper
    ): DBWrapper

    @Binds
    @Singleton
    abstract fun bindSaveUserApiKeys(
        saveUserApiKeysImpl: SaveUserApiKeysImpl
    ): SaveUserApiKeys

    @Binds
    @Singleton
    fun bindsUserKeysRepository(
        userKeysRepositoryImpl: UserKeysRepositoryImpl
    ): UserKeysRepository

    @Binds
    @Singleton
    fun bindsKeysChanged(
        keysChangedImpl: KeysChangedImpl
    ): KeysChanged

    @Binds
    @Singleton
    fun bindsGetUserApiKeys(
        GetUserApiKeysImpl: GetUserApiKeysImpl
    ):  GetUserApiKeys


    @Binds
    @Singleton
    fun bindErrorHandling(
        ErrorHandlingImpl: ErrorHandlingImpl
    ): ErrorHandling


}










@Module
@InstallIn(SingletonComponent::class)


object  NetworkModule {
    //Retrofit

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMarvelApiService(retrofit: Retrofit): MarvelApi {
        return retrofit.create(MarvelApi::class.java)
    }


}







    @Module
    @InstallIn(SingletonComponent::class)
        object SharedPreferencesModule {
            @Provides
            fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
                return context.getSharedPreferences("publicKey", Context.MODE_PRIVATE)
            }

        @Provides
        fun provideMyPreferences(@ApplicationContext context: Context): MyPreferences {
            return MyPreferences(context)
            }

        @Provides
        fun provideUserKeysRepository(myPreferences: MyPreferences): UserKeysRepositoryImpl {
            return UserKeysRepositoryImpl(myPreferences)
        }





        }



