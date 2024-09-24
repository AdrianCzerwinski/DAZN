package pl.adrianczerwinski.dazn.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Locale
import javax.inject.Singleton

@Suppress("UnnecessaryAbstractClass")
@Module
@InstallIn(SingletonComponent::class)
object HiltCommonModule {

    @Provides
    @Singleton
    fun provideLocale(): Locale = Locale.getDefault()
}
