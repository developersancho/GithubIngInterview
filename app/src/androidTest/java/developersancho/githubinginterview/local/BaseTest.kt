package developersancho.githubinginterview.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import developersancho.githubinginterview.data.local.db.CoreDatabase
import developersancho.githubinginterview.di.localModule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
abstract class BaseTest : KoinTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    protected val database by inject<CoreDatabase>()

    @Before
    open fun setUp() {
        this.configureDi()
    }

    @After
    open fun tearDown() {
        stopKoin()
    }

    // CONFIGURATION
    private fun configureDi() {
        startKoin {
            module {
                single {
                    Room.inMemoryDatabaseBuilder(
                        ApplicationProvider.getApplicationContext<Context>(),
                        CoreDatabase::class.java
                    ).allowMainThreadQueries().build()
                }
                factory { get<CoreDatabase>().favDao() }
            }
        }
    }
}