package developersancho.githubinginterview.utils.helper

import android.content.Context
import android.os.Bundle
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator


private const val IS_RECREATED = "isRecreated"

@Navigator.Name("retain_state_fragment")
class RetainStateFragmentNavigator(
    private val context: Context,
    private val manager: androidx.fragment.app.FragmentManager,
    private val containerId: Int
) : FragmentNavigator(context, manager, containerId) {

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Navigator.Extras?
    ): NavDestination? {
        val tag = destination.id.toString()
        val transaction = manager.beginTransaction()

        val currentFragment = manager.primaryNavigationFragment
        if (currentFragment != null) {
            transaction.detach(currentFragment)
        }

        var fragment = manager.findFragmentByTag(tag)
        if (fragment == null) {
            val className = destination.className
            fragment = instantiateFragment(context, manager, className, args)
            transaction.add(containerId, fragment, tag)
        } else {
            transaction.attach(fragment)
        }

        transaction.setPrimaryNavigationFragment(fragment)
        transaction.setReorderingAllowed(true)
        transaction.commit()

        return destination
    }
}