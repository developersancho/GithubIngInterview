package developersancho.githubinginterview.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import developersancho.githubinginterview.data.repository.DataManager
import developersancho.githubinginterview.ui.detail.DetailViewModel
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class DetailUnitTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var dataManager: DataManager
    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setUp() {
        dataManager = mockk()
        detailViewModel = DetailViewModel(dataManager)
    }


    @Test
    fun `Repo Fav clicks on star image`() {

    }
}