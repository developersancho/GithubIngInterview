package developersancho.githubinginterview.ui.home


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import developersancho.githubinginterview.R
import developersancho.githubinginterview.data.remote.model.Repos
import developersancho.githubinginterview.databinding.FragmentHomeBinding
import developersancho.githubinginterview.ui.base.BaseFragment
import developersancho.githubinginterview.ui.detail.DetailDialogFragment
import developersancho.githubinginterview.utils.helper.DividerItemDecoration
import developersancho.githubinginterview.utils.helper.SingleEventObserver
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel by viewModel<HomeViewModel>()
    private var adapter: RepoAdapter? = null
    private var repoList: List<Repos>? = null

    companion object {
        private val TAG = HomeFragment::class.java.simpleName
    }

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun initPresenter() {
        viewModel.setPresenter(this)
    }

    override fun bindingUI() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    private fun subscribeUI() {
        viewModel.repos.observe(this, SingleEventObserver {
            repoList = it
            adapter?.submitList(repoList)
        })
    }

    override fun initUI(view: View, savedInstanceState: Bundle?) {
        initAdapter()
        subscribeUI()
    }

    private fun initAdapter() {
        adapter = RepoAdapter()
        binding.rvUserRepos.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                R.drawable.bg_divider
            )
        )
        binding.rvUserRepos.adapter = adapter

        adapter?.onItemClick = { repo ->
            val model = DetailDialogFragment.Property()
            model.repoId = repo.id
            model.userName = repo.owner?.login
            model.repoName = repo.name
            model.avatarUrl = repo.owner?.avatarUrl
            model.starCount = "Stars: ${repo.stargazersCount}"
            model.issueCount = "Open Issues: ${repo.openIssuesCount}"
            model.isFav = repo.isFav
            model.onRefresh = { value ->
                repoList?.find { it.id == model.repoId }?.isFav = value
                adapter?.submitList(repoList)
                adapter?.notifyDataSetChanged()
            }

            DetailDialogFragment.newInstance(model)
                .show(requireFragmentManager().beginTransaction(), "detail")
        }
    }

    override fun initListener() {
        binding.btnSubmit.setOnClickListener {
            if (validate()) {
                hideKeyboard(requireActivity())
                getData()
            }
        }
    }

    private fun getData() {
        val userName = binding.etUserName.text.toString()
        viewModel.getRepos(user = userName)
    }

    private fun validate(): Boolean {
        if (binding.etUserName.text.isNullOrEmpty()) {
            binding.tilUserName.error = getString(R.string.error_message_enter_user_name)
            binding.etUserName.requestFocus()
            return false
        } else binding.tilUserName.isErrorEnabled = false


        return true
    }

    override fun onDestroyView() {
        adapter = null
        super.onDestroyView()
    }
}
