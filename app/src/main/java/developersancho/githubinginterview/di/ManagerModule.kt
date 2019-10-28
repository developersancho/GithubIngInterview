package developersancho.githubinginterview.di

import developersancho.githubinginterview.data.local.ILocalManager
import developersancho.githubinginterview.data.local.LocalManager
import developersancho.githubinginterview.data.remote.IRemoteManager
import developersancho.githubinginterview.data.remote.RemoteManager
import developersancho.githubinginterview.data.repository.DataManager
import developersancho.githubinginterview.data.repository.IDataManager
import org.koin.dsl.module

val managerModule = module {
    single { DataManager(get(), get()) as IDataManager }
    single { RemoteManager(get()) as IRemoteManager }
    single { LocalManager(get()) as ILocalManager }
}