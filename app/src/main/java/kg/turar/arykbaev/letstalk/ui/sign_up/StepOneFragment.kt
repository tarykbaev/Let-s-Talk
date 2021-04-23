package kg.turar.arykbaev.letstalk.ui.sign_up

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import kg.turar.arykbaev.letstalk.App
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentStepOneBinding
import kg.turar.arykbaev.letstalk.domain.Event
import kg.turar.arykbaev.letstalk.extension.isValidEmail
import kg.turar.arykbaev.letstalk.extension.showWarningSnackbar
import kg.turar.arykbaev.letstalk.extension.toTextString
import kg.turar.arykbaev.letstalk.ui.base.BaseFragment


class StepOneFragment : BaseFragment<FragmentStepOneBinding, SignUpVM>(SignUpVM::class.java) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity?.application as App).appComponent.inject(this)
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
        vm.isNewEmail(email).observe(viewLifecycleOwner, {
            when(it) {
                true -> navigate(R.id.action_registrationFragment_to_stepTwoFragment)
                false -> showWarningSnackbar("This email address already exists", ui.btnSignUp)
            }
        })
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