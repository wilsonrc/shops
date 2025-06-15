package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.ShopDao
import com.example.data.local.ShopDatabase
import com.example.data.remote.AssetJsonReader
import com.example.data.repository.ShopsRepositoryImpl
import com.example.domain.repository.ShopsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindRepo(impl: ShopsRepositoryImpl): ShopsRepository

    companion object {

        /** Shared IO dispatcher used across the data layer. */
        @Provides
        @Singleton
        fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

        @Provides
        @Singleton
        fun db(@ApplicationContext ctx: Context): ShopDatabase =
            Room.databaseBuilder(ctx, ShopDatabase::class.java, "shops.db")
                .fallbackToDestructiveMigration()
                .build()

        @Provides
        fun dao(db: ShopDatabase): ShopDao = db.shopDao()

        @Provides
        fun jsonReader(
            @ApplicationContext ctx: Context,
            dispatcher: CoroutineDispatcher
        ): AssetJsonReader = AssetJsonReader(ctx, dispatcher)
    }
}