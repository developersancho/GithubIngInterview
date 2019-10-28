package developersancho.githubinginterview.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import developersancho.githubinginterview.R
import developersancho.githubinginterview.data.remote.model.Repos
import developersancho.githubinginterview.databinding.ItemUserRepoBinding
import developersancho.githubinginterview.ui.base.BaseViewHolder
import developersancho.githubinginterview.utils.extension.inflate

class RepoAdapter(private val viewModel: HomeViewModel) :
    ListAdapter<Repos, RepoAdapter.RepoViewHolder>(DiffCallback()) {

    var onItemClick: ((Repos) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(parent.inflate(R.layout.item_user_repo))
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.apply {
            bind(getItem(position), viewModel)
            itemView.tag = getItem(position)
        }
    }

    inner class RepoViewHolder(view: View) : BaseViewHolder<ItemUserRepoBinding>(view) {
        fun bind(repo: Repos, viewModel: HomeViewModel) {
            binding.repo = repo
            binding.vm = viewModel
            binding.root.setOnClickListener {
                onItemClick?.invoke(repo)
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Repos>() {

    override fun areItemsTheSame(oldItem: Repos, newItem: Repos): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repos, newItem: Repos): Boolean {
        return oldItem == newItem
    }
}