package developersancho.githubinginterview.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavRepo_Table")
class FavRepo(
    @PrimaryKey
    var repoId: Int? = 0,
    var isFav: Boolean = false
)