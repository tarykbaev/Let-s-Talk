package kg.turar.arykbaev.letstalk.presentation.login

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentLoginBinding
import kg.turar.arykbaev.letstalk.extension.isValidEmail
import kg.turar.arykbaev.letstalk.extension.showWarningSnackbar
import kg.turar.arykbaev.letstalk.extension.toTextString
import kg.turar.arykbaev.letstalk.presentation.base.BaseFragment


class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupTextWatchers()

        ui.btnLogin.setOnClickListener {
            signin()
        }
        ui.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_login_fragment_to_registrationFragment)
        }
    }

    private fun signin() {
        when(ui.inputEmail.toTextString().isValidEmail()) {
            true -> login()
            else -> showWarningSnackbar("Incorrect email address", ui.btnLogin)
        }
    }

    private fun login() {
        mAuth.signInWithEmailAndPassword(ui.inputEmail.toTextString(), ui.inputPassword.toTextString())
            .addOnSuccessListener {
                when(mAuth.currentUser?.isEmailVerified) {
                    true -> findNavController().navigate(R.id.action_login_fragment_to_chat_fragment)
                    else -> showWarningSnackbar("Please, Verify your email", ui.btnLogin)
                }
            }
            .addOnFailureListener {
                showWarningSnackbar("Incorrect email or password", ui.btnLogin)
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

    override fun performViewBinding(): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(layoutInflater)
    }
}