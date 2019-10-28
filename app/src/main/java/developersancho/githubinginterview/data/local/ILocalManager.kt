package developersancho.githubinginterview.data.local

import developersancho.githubinginterview.data.local.model.FavRepo

interface ILocalManager {
    suspend fun insertFavRepo(favRepo: FavRepo)
    suspend fun favRepos(): List<FavRepo>
    suspend fun favRepo(id: Int): FavRepo
}