package developersancho.githubinginterview.utils.extension

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import developersancho.githubinginterview.R

fun ImageView.load(@DrawableRes resourceId: Int) {
    Glide.with(context).load(resourceId).placeholder(R.drawable.ic_john_wick)
        .into(this)
}

fun ImageView.load(url: String) {
    Glide.with(context).load(url).placeholder(R.drawable.ic_john_wick)
        .into(this)
}