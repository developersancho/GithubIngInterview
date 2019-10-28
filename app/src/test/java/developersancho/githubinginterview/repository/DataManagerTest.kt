package developersancho.githubinginterview.repository

import androidx.lifecycle.Observer
import developersancho.githubinginterview.data.local.LocalManager
import developersancho.githubinginterview.data.local.dao.FavDao
import developersancho.githubinginterview.data.remote.RemoteManager
import developersancho.githubinginterview.data.remote.model.Repos
import developersancho.githubinginterview.data.remote.network.IRepoService
import developersancho.githubinginterview.data.remote.network.ResponseWrapper
import developersancho.githubinginterview.data.repository.DataManager
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import timber.log.Timber

@ExperimentalCoroutinesApi
class DataManagerTest {
    private lateinit var observerRepo: Observer<ResponseWrapper<ArrayList<Repos>>>
    private lateinit var dataManager: DataManager
    private val service = mockk<IRepoService>(relaxed = true)
    private val dao = mockk<FavDao>(relaxed = true)

    @Before
    fun setUp() {
        dataManager = DataManager(RemoteManager(service), LocalManager(dao))
    }


    @Test
    fun `Get Repo`() {
        runBlocking {
            when (val result = dataManager.getRepos("developersancho")) {
                is ResponseWrapper.Success -> {
                    Assert.assertEquals("30", result.data.size)
                }
                is ResponseWrapper.Error -> {
                    Timber.d(result.exception.message)
                }
            }
        }
    }
}