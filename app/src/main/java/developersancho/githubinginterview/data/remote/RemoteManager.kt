package developersancho.githubinginterview.data.remote

import developersancho.githubinginterview.data.remote.model.Repos
import developersancho.githubinginterview.data.remote.network.IRepoService
import retrofit2.Response

class RemoteManager(private val repoService: IRepoService) : IRemoteManager {

    override suspend fun getRepos(user: String): Response<ArrayList<Repos>> = repoService.repos(user)

}