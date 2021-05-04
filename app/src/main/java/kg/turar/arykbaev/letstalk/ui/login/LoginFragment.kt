package kg.turar.arykbaev.letstalk.ui.login

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import kg.turar.arykbaev.letstalk.App
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentLoginBinding
import kg.turar.arykbaev.letstalk.domain.Event
import kg.turar.arykbaev.letstalk.extension.isValidEmail
import kg.turar.arykbaev.letstalk.extension.showWarningSnackbar
import kg.turar.arykbaev.letstalk.extension.toText
import kg.turar.arykbaev.letstalk.ui.base.BaseFragment


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginVM>(LoginVM::class.java) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity?.application as App).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        subscribeToLiveData()
    }

    private fun setupViews() {
        setupTextWatchers()
        setupListeners()
    }

    private fun setupListeners() {
        ui.btnLogin.setOnClickListener {
            login()
        }
        ui.tvSignUp.setOnClickListener {
            navigate(R.id.action_login_fragment_to_registrationFragment)
        }
    }

    private fun subscribeToLiveData() {
        vm.event.observe(viewLifecycleOwner, {
            when(it) {
                is Event.Notification -> showWarningSnackbar(it.message, ui.btnLogin)
                is Event.Success -> findNavController().navigate(R.id.action_login_fragment_to_chat_fragment)
            }
        })
    }

    private fun login() {
        when(ui.inputEmail.toText.isValidEmail()) {
            true -> vm.login(ui.inputEmail.toText, ui.inputPassword.toText)
            else -> showWarningSnackbar("Incorrect email address", ui.btnLogin)
        }
    }

    private fun setupTextWatchers() {
        ui.inputEmail.addTextChangedListener { toggleButtonState() }
        ui.inputPassword.addTextChangedListener { toggleButtonState() }
    }

    private fun toggleButtonState() {
        ui.btnLogin.isEnabled = isFieldValid()
    }

    private fun isFieldValid(): Boolean {
        return ui.inputEmail.text.isNotEmpty() && ui.inputPassword.text.isNotEmpty()
    }

    override fun performViewBinding() = FragmentLoginBinding.inflate(layoutInflater)
}