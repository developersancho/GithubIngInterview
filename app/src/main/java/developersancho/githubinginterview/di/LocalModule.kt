package developersancho.githubinginterview.di

import androidx.room.Room
import developersancho.githubinginterview.data.local.db.CoreDatabase
import developersancho.githubinginterview.utils.AppConstant
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localModule = module {
    single {
        Room.databaseBuilder(androidApplication(), CoreDatabase::class.java, AppConstant.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<CoreDatabase>().favDao() }
}