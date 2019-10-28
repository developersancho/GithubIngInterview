package developersancho.githubinginterview.di

import developersancho.githubinginterview.data.remote.network.IRepoService
import developersancho.githubinginterview.data.remote.network.ServiceClient
import org.koin.dsl.module

val remoteModule = module {
    factory { ServiceClient.createWebService<IRepoService>() }
}