package developersancho.githubinginterview.ui.base

import androidx.lifecycle.ViewModel
import developersancho.githubinginterview.data.repository.IDataManager
import java.lang.ref.WeakReference

abstract class BaseViewModel<P>(val dataManager: IDataManager) : ViewModel(){
    lateinit var presenter: WeakReference<P>

    fun getPresenter(): P {
        return presenter.get()!!
    }

    fun setPresenter(presenter: P) {
        this.presenter = WeakReference(presenter)
    }
}