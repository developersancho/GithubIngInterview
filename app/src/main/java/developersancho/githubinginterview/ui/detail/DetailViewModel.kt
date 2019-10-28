package developersancho.githubinginterview.ui.detail

import androidx.lifecycle.viewModelScope
import developersancho.githubinginterview.data.local.model.FavRepo
import developersancho.githubinginterview.data.repository.IDataManager
import developersancho.githubinginterview.ui.base.BaseViewModel
import developersancho.githubinginterview.ui.base.IBasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(dataManager: IDataManager) : BaseViewModel<IBasePresenter>(dataManager) {
    var model: DetailDialogFragment.Property? = null

    fun insertFavRepo(favRepo: FavRepo) = viewModelScope.launch(Dispatchers.IO) {
        dataManager.insertFavRepo(favRepo = favRepo)
    }


}