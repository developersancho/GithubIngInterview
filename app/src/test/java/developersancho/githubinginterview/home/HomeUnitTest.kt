package developersancho.githubinginterview.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import developersancho.githubinginterview.data.remote.model.Repos
import developersancho.githubinginterview.data.remote.network.ResponseWrapper
import developersancho.githubinginterview.data.repository.DataManager
import developersancho.githubinginterview.ui.home.HomeViewModel
import developersancho.githubinginterview.utils.helper.SingleEvent
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class HomeUnitTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var dataManager: DataManager
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        dataManager = mockk()
        homeViewModel = HomeViewModel(dataManager)
    }

    @Test
    fun `Repo requested when ViewModel is created`() {
        val observer = mockk<Observer<SingleEvent<ArrayList<Repos>>>>(relaxed = true)
        val result = ResponseWrapper.Success(FAKE_REPO)
        homeViewModel.repos.observeForever(observer)

        verify {
            //observer.onChanged(result)
        }

        confirmVerified(observer)
    }

    @Test
    fun `Repo requested but failed when ViewModel is created`() {

    }

    @Test
    fun `Repo clicks on item on RecyclerView`() {

    }

}

val FAKE_REPO = arrayListOf(
    Repos(), Repos()
)