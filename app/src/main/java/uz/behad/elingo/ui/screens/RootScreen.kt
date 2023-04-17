package uz.behad.elingo.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.behad.elingo.R
import uz.behad.elingo.databinding.ScreenRootBinding

class RootScreen : Fragment(R.layout.screen_root) {

    private val binding by viewBinding(ScreenRootBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bottomNavigation.itemIconTintList = null
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        var navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)

    }

}