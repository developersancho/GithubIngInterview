package developersancho.githubinginterview.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import developersancho.githubinginterview.data.local.dao.FavDao
import developersancho.githubinginterview.data.local.model.FavRepo

@Database(
    entities = [FavRepo::class],
    version = 2,
    exportSchema = false
)
abstract class CoreDatabase : RoomDatabase() {
    abstract fun favDao(): FavDao
}