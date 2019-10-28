package developersancho.githubinginterview.di

import developersancho.githubinginterview.ui.detail.DetailViewModel
import developersancho.githubinginterview.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}