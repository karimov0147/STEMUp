package uz.behad.elingo.ui.screens

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import uz.behad.elingo.R


class SplashScreen : Fragment(R.layout.screen_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        lifecycleScope.launchWhenCreated {
//            requireActivity().findNavController(R.id.navHostFragment)
//                .navigate(R.id.action_splashScreen_to_root_navigation)
//                delay(200)
//        }

        Handler().postDelayed({
            if (isAdded)
                requireActivity().findNavController(R.id.navHostFragment)
                    .navigate(R.id.action_splashScreen_to_root_navigation)
        }, 2000)
    }
}