package me.shtanko.network.di

import dagger.Component
import me.shtanko.network.NetworkClient
import javax.inject.Singleton

interface NetworkProvider {
    fun provideNetworkClient(): NetworkClient
}

@Singleton
@Component(modules = [RestModule::class, NetworkModule::class])
interface NetworkComponent : NetworkProvider {


}