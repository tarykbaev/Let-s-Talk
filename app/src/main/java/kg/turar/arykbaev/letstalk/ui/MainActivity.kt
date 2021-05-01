package kg.turar.arykbaev.letstalk.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kg.turar.arykbaev.letstalk.App
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.ActivityMainBinding
import kg.turar.arykbaev.letstalk.domain.UserState
import kg.turar.arykbaev.letstalk.extension.gone
import kg.turar.arykbaev.letstalk.extension.visible
import kg.turar.arykbaev.letstalk.ui.base.BaseActivity


class MainActivity : BaseActivity<ActivityMainBinding, MainVM>(MainVM::class.java) {

    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        val fragments = listOf(
            R.id.login_fragment,
            R.id.stepOneFragment,
            R.id.stepTwoFragment,
            R.id.stepThreeFragment,
            R.id.verifyMailFragment
        )

        navController = Navigation.findNavController(this, R.id.fragment)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (fragments.contains(destination.id)) {
                true -> ui.bottomNav.gone()
                else -> ui.bottomNav.visible()
            }
        }

        ui.bottomNav.setupWithNavController(navController)
    }

    override fun performBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onResume() {
        super.onResume()
        vm.changeUserState(UserState.ONLINE)
    }

    override fun onStop() {
        super.onStop()
        vm.changeUserState(UserState.OFFLINE)
    }
}