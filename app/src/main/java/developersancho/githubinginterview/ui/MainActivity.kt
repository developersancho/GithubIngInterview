package developersancho.githubinginterview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import developersancho.githubinginterview.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*val navController = findNavController(R.id.nav_host_fragment)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)!!
        val navigator =
            RetainStateFragmentNavigator(
                this,
                navHostFragment.childFragmentManager,
                R.id.nav_host_fragment
            )
        navController.navigatorProvider += navigator
        navController.setGraph(R.navigation.nav_graph_home)*/
    }

    override fun onSupportNavigateUp(): Boolean =
        findNavController(R.id.nav_host_fragment).navigateUp()

}
