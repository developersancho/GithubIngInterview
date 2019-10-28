package developersancho.githubinginterview.ui.base

interface IBasePresenter {
    fun showLoading()
    fun hideLoading()
    fun onServerError(message: String, code: Int)
    fun onServiceError(message: String, code: String)
}