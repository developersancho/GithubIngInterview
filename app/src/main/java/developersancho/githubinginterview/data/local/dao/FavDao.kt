package developersancho.githubinginterview.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import developersancho.githubinginterview.data.local.model.FavRepo

@Dao
interface FavDao : BaseDao<FavRepo> {
    @Query("DELETE FROM FavRepo_Table")
    suspend fun deleteAll()

    @Query("select * from FavRepo_Table")
    suspend fun favRepos(): List<FavRepo>

    @Query("select * from FavRepo_Table where repoId= :id")
    suspend fun favRepo(id: Int): FavRepo
}