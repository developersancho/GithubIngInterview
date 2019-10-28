package developersancho.githubinginterview.utils

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import developersancho.githubinginterview.R
import developersancho.githubinginterview.utils.extension.load

object BindingUtils {

    @JvmStatic
    @BindingAdapter("toImageResourcesId")
    fun showImage(view: ImageView, @DrawableRes resourcesId: Int? = null) {
        if (resourcesId != null) {
            view.load(resourcesId)
        } else {
            view.load(R.drawable.ic_john_wick)
        }
    }

    @JvmStatic
    @BindingAdapter("toImageUrl")
    fun showImage(view: ImageView, url: String? = null) {
        if (url != null) {
            view.load(url)
        } else {
            view.load(R.drawable.ic_john_wick)
        }
    }

    @JvmStatic
    @BindingAdapter("favImage")
    fun showImage(view: ImageView, isFav: Boolean = false) {
        if (isFav) {
            view.load(R.drawable.ic_star_full_24dp)
        } else {
            view.load(R.drawable.ic_star_border_24dp)
        }
    }

    @JvmStatic
    @BindingAdapter("favListImage")
    fun showFavImage(view: ImageView, showFav: Boolean = false) {
        if (showFav) {
            view.visibility = View.VISIBLE
            view.load(R.drawable.ic_star_full_24dp)
        } else {
            view.visibility = View.GONE
        }
    }

    @BindingAdapter("data")
    fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: T) {
        if (recyclerView.adapter is BindableAdapter<*>) {
            (recyclerView.adapter as BindableAdapter<T>).setData(data)
        }
    }
}

interface BindableAdapter<T> {
    fun setData(data: T)
}