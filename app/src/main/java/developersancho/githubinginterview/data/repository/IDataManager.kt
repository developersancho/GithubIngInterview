package developersancho.githubinginterview.data.repository

import developersancho.githubinginterview.data.local.ILocalManager
import developersancho.githubinginterview.data.remote.model.Repos
import developersancho.githubinginterview.data.remote.network.ResponseWrapper

interface IDataManager : ILocalManager {
    suspend fun getRepos(user: String): ResponseWrapper<ArrayList<Repos>>
}