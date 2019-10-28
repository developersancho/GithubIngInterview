package developersancho.githubinginterview.utils.extension

import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.andrognito.flashbar.Flashbar
import developersancho.githubinginterview.R

fun FragmentActivity.showFlashBar(message: String) {
    Flashbar.Builder(this)
        .gravity(Flashbar.Gravity.BOTTOM)
        .message(message)
        .messageColor(
            ContextCompat.getColor(
                this, R.color.white
            )
        )
        .duration(1500)
        .messageSizeInSp(16f)
        .vibrateOn(Flashbar.Vibration.SHOW)
        .backgroundColorRes(R.color.orange)
        .build().show()
}