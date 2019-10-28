package developersancho.githubinginterview.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import developersancho.githubinginterview.data.local.model.FavRepo
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FavorDaoTest : BaseTest() {

    /*
    "java.lang.RuntimeException: Delegate runner 'org.robolectric.RobolectricTestRunner' for AndroidJUnit4 could not be loaded."
        hatası alanıyor. Robolectric Testing with AndroidJunitRunner olarak revize edilecek.
     */

    @Test
    fun insertFavRepo() {
        runBlocking {
            database.favDao().insert(
                FavRepo(
                    repoId = 117545989,
                    isFav = true
                )
            )

            val fav = database.favDao().favRepo(117545989)
            assertEquals(117545989, fav.repoId)
            assertEquals(true, fav.isFav)
        }
    }


    @Test
    fun getFavRepo() {
        runBlocking {
            val fav = database.favDao().favRepo(117545989)
            assertEquals(117545989, fav.repoId)
            assertEquals(true, fav.isFav)
        }
    }
}