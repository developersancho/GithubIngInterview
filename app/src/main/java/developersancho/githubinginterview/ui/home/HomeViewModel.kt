package developersancho.githubinginterview.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import developersancho.githubinginterview.data.remote.model.Repos
import developersancho.githubinginterview.data.remote.network.ResponseWrapper
import developersancho.githubinginterview.data.repository.IDataManager
import developersancho.githubinginterview.ui.base.BaseViewModel
import developersancho.githubinginterview.ui.base.IBasePresenter
import developersancho.githubinginterview.utils.helper.SingleEvent
import kotlinx.coroutines.launch

class HomeViewModel(dataManager: IDataManager) :
    BaseViewModel<IBasePresenter>(dataManager) {

    val repos: MutableLiveData<SingleEvent<ArrayList<Repos>>> = MutableLiveData()

    fun getRepos(user: String) = viewModelScope.launch {
        getPresenter().showLoading()
        when (val result = dataManager.getRepos(user)) {
            is ResponseWrapper.Success -> {
                val repoList = result.data
                val favList = dataManager.favRepos()
                favList.forEach { favRepo ->
                    repoList.find { it.id == favRepo.repoId }?.isFav = favRepo.isFav
                }

                repos.value = SingleEvent(repoList)
                getPresenter().hideLoading()
            }

            is ResponseWrapper.Error -> {
                getPresenter().hideLoading()
                getPresenter().onServerError(result.exception.message, result.exception.code)
            }
        }
    }
}