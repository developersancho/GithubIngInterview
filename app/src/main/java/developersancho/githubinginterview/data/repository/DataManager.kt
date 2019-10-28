package developersancho.githubinginterview.data.repository

import developersancho.githubinginterview.data.local.ILocalManager
import developersancho.githubinginterview.data.local.model.FavRepo
import developersancho.githubinginterview.data.remote.IRemoteManager
import developersancho.githubinginterview.data.remote.model.Repos
import developersancho.githubinginterview.data.remote.network.ResponseWrapper
import developersancho.githubinginterview.data.repository.base.BaseDataManager

class DataManager(
    private val remoteManager: IRemoteManager,
    private val localManager: ILocalManager
) : BaseDataManager(), IDataManager {

    override suspend fun getRepos(user: String): ResponseWrapper<ArrayList<Repos>> =
        resultWrapperSuspend { remoteManager.getRepos(user) }

    override suspend fun insertFavRepo(favRepo: FavRepo) = localManager.insertFavRepo(favRepo)

    override suspend fun favRepos(): List<FavRepo> = localManager.favRepos()

    override suspend fun favRepo(id: Int): FavRepo = localManager.favRepo(id)
}