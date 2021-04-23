package kg.turar.arykbaev.letstalk.ui.sign_up

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import kg.turar.arykbaev.letstalk.App
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentVerifyMailBinding
import kg.turar.arykbaev.letstalk.extension.showWarningSnackbar
import kg.turar.arykbaev.letstalk.ui.base.BaseFragment


class VerifyMailFragment : BaseFragment<FragmentVerifyMailBinding, SignUpVM>(SignUpVM::class.java) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity?.application as App).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        onBackPressed()
        setupListeners()
    }

    private fun setupListeners() {
        ui.tvSkip.setOnClickListener {
            navigateToLogin()
        }
        ui.btnOpenApp.setOnClickListener {
            openMailApp()
            navigateToLogin()
        }
    }

    private fun openMailApp() {
        try {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_APP_EMAIL)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            requireActivity().startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            showWarningSnackbar("There is no email client installed")
        }
    }

    private fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        findNavController().navigate(R.id.action_verifyMailFragment_to_login_fragment)
    }

    override fun performViewBinding() = FragmentVerifyMailBinding.inflate(layoutInflater)
}