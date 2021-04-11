package kg.turar.arykbaev.letstalk.presentation.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentRegistrationBinding
import kg.turar.arykbaev.letstalk.presentation.BaseFragment

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        ui.btnSignUp.setOnClickListener {
            val email = ui.editTextTextEmailAddress.text.toString()
            val password = ui.editTextTextPassword.text.toString()

            mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                mAuth.currentUser.sendEmailVerification().addOnSuccessListener {
                    Toast.makeText(requireContext(), "Email send", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_registrationFragment_to_login_fragment)
                }
            }
        }

    }

    override fun performViewBinding(): FragmentRegistrationBinding {
        return FragmentRegistrationBinding.inflate(layoutInflater)
    }
}