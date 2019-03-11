package me.shtanko.core

import me.shtanko.domain.interactor.GetUser
import me.shtanko.domain.interactor.GetUsers

interface ApplicationProvider : MainToolsProvider, RepositoryProvider, CommonAndroidProvider

interface CommonAndroidProvider

interface MainToolsProvider {
    fun provideContext(): App
}

interface RepositoryProvider {
    fun provideGetUser(): GetUser
    fun provideGetUsers(): GetUsers
}