package developersancho.githubinginterview.ui.base

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import developersancho.githubinginterview.utils.extension.showToast
import developersancho.githubinginterview.utils.helper.AutoClearedValue
import developersancho.githubinginterview.utils.widget.LoadingLottieDialog

abstract class BaseFragment<T : ViewDataBinding> : Fragment(), IBasePresenter {
    protected var binding: T by AutoClearedValue<T>()

    private val dialog: AlertDialog by lazy {
        LoadingLottieDialog.Builder().setContext(context!!)
            .setCancelable(false)
            .build()
    }

    @get:LayoutRes
    protected abstract val layoutId: Int

    protected abstract fun initPresenter()

    protected abstract fun bindingUI()

    protected abstract fun initUI(view: View, savedInstanceState: Bundle?)

    protected abstract fun initListener()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initPresenter()
        return DataBindingUtil.inflate<T>(
            inflater,
            layoutId,
            container,
            false
        ).apply { binding = this }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingUI()
        initUI(view, savedInstanceState)
        initListener()
    }

    override fun showLoading() {
        if (!dialog.isShowing)
            dialog.show()
    }

    override fun hideLoading() {
        if (dialog.isShowing)
            dialog.dismiss()
    }

    override fun onServerError(message: String, code: Int) {
        requireContext().showToast(message)
    }

    override fun onServiceError(message: String, code: String) {

    }

    protected fun hideKeyboard(activity: Activity) {
        val inputManager = activity
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // check if no view has focus:
        val currentFocusedView = activity.currentFocus
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(
                currentFocusedView.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}