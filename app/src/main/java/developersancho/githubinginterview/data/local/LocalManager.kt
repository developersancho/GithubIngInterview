package developersancho.githubinginterview.data.local

import developersancho.githubinginterview.data.local.dao.FavDao
import developersancho.githubinginterview.data.local.model.FavRepo

class LocalManager(private val favDao: FavDao) : ILocalManager {

    override suspend fun insertFavRepo(favRepo: FavRepo) = favDao.insert(favRepo)

    override suspend fun favRepos(): List<FavRepo> = favDao.favRepos()

    override suspend fun favRepo(id: Int): FavRepo = favDao.favRepo(id)
}