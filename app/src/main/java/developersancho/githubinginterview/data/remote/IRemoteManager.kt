package developersancho.githubinginterview.data.remote

import developersancho.githubinginterview.data.remote.model.Repos
import retrofit2.Response

interface IRemoteManager {
    suspend fun getRepos(user: String): Response<ArrayList<Repos>>
}