package developersancho.githubinginterview

import android.app.Application
import android.content.Context
import developersancho.githubinginterview.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoreApp : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        configureDi()
    }

    private fun configureDi() = startKoin {
        androidContext(this@CoreApp)
        modules(appModule)
    }
}