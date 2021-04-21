package kg.turar.arykbaev.letstalk.presentation.sign_up

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentStepOneBinding
import kg.turar.arykbaev.letstalk.extension.isValidEmail
import kg.turar.arykbaev.letstalk.extension.showWarningSnackbar
import kg.turar.arykbaev.letstalk.extension.toTextString
import kg.turar.arykbaev.letstalk.presentation.base.BaseFragment

class StepOneFragment : BaseFragment<FragmentStepOneBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupTextWatchers()
        ui.btnSignUp.setOnClickListener {
            emailValidation(ui.inputEmail.toTextString())
        }
    }

    private fun setupTextWatchers() {
        addTextChangedListener(ui.inputName)
        addTextChangedListener(ui.inputEmail)
        addTextChangedListener(ui.inputPassword)
    }

    private fun addTextChangedListener(input: EditText) {
        input.addTextChangedListener { toggleButtonState() }
    }

    private fun emailValidation(email: String) {
        when (email.isValidEmail()) {
            true -> nextValidation(email)
            else -> showWarningSnackbar("Manas University email only", ui.btnSignUp)
        }
    }

    private fun nextValidation(email: String) {
        mAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener {
            when (it.result?.signInMethods.isNullOrEmpty()) {
                true -> nextScreen()
                else -> showWarningSnackbar("This email address already exists", ui.btnSignUp)
            }
        }
    }

    private fun nextScreen() {
        findNavController().navigate(R.id.action_registrationFragment_to_stepTwoFragment)
    }

    private fun toggleButtonState() {
        ui.btnSignUp.isEnabled = isFieldValid()
    }

    private fun isFieldValid(): Boolean {
        return isNotEmpty(ui.inputName) && isNotEmpty(ui.inputEmail) && isValidPassword(ui.inputPassword)
    }

    private fun isNotEmpty(input: EditText): Boolean = input.text.isNotEmpty()

    private fun isValidPassword(input: EditText): Boolean {
        return input.text.isNotEmpty() && input.text.length >= 6
    }

    override fun performViewBinding() = FragmentStepOneBinding.inflate(layoutInflater)
}