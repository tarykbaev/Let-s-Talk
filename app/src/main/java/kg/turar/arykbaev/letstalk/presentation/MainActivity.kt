package kg.turar.arykbaev.letstalk.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.extension.gone
import kg.turar.arykbaev.letstalk.extension.visible

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottom_nav)
        navController = Navigation.findNavController(this, R.id.fragment)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id == R.id.login_fragment || destination.id == R.id.registrationFragment) {
                true -> bottomNav.gone()
                else -> bottomNav.visible()
            }
        }

        bottomNav.setupWithNavController(navController)
    }
}