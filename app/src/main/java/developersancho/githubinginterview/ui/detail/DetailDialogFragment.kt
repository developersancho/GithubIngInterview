package developersancho.githubinginterview.ui.detail

import android.os.Bundle
import android.view.View
import developersancho.githubinginterview.R
import developersancho.githubinginterview.data.local.model.FavRepo
import developersancho.githubinginterview.databinding.FragmentDetailBinding
import developersancho.githubinginterview.ui.base.BaseDialogFragment
import developersancho.githubinginterview.utils.extension.load
import developersancho.githubinginterview.utils.extension.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.Serializable

class DetailDialogFragment : BaseDialogFragment<FragmentDetailBinding>() {
    private val viewModel by viewModel<DetailViewModel>()
    private var model: Property? = null

    companion object {
        private val TAG = DetailDialogFragment::class.java.simpleName

        fun newInstance(model: Property) = DetailDialogFragment().apply {
            setStyle(STYLE_NORMAL, R.style.DialogFragmentTheme)
            this.model = model
        }
    }

    class Property : Serializable {
        var repoId: Int? = null
        var repoName: String? = null
        var userName: String? = null
        var avatarUrl: String? = null
        var starCount: String? = null
        var issueCount: String? = null
        var isFav: Boolean? = null
        var onRefresh: ((Boolean) -> Unit)? = null
    }

    override val layoutId: Int
        get() = R.layout.fragment_detail

    override fun initPresenter() {
        viewModel.setPresenter(this)
    }

    override fun bindingUI() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    override fun initUI(view: View, savedInstanceState: Bundle?) {
        viewModel.model = model
    }

    override fun initListener() {
        binding.toolbarDetail.setNavigationOnClickListener {
            dialog?.dismiss()
        }

        binding.ivStar.setOnClickListener {
            val isFav = viewModel.model?.isFav!!
            viewModel.model?.isFav = !isFav

            viewModel.insertFavRepo(
                FavRepo(
                    repoId = viewModel.model?.repoId,
                    isFav = viewModel.model?.isFav!!
                )
            )
            if (viewModel.model?.isFav!!) {
                binding.ivStar.load(R.drawable.ic_star_full_24dp)
                requireContext().showToast(getString(R.string.message_added_favorite))
                if (model?.onRefresh != null) {
                    model?.onRefresh?.invoke(viewModel.model?.isFav!!)
                }
            } else {
                binding.ivStar.load(R.drawable.ic_star_border_24dp)
                requireContext().showToast(getString(R.string.message_removed_favorite))
                if (model?.onRefresh != null) {
                    model?.onRefresh?.invoke(viewModel.model?.isFav!!)
                }
            }

        }
    }
}