package kg.turar.arykbaev.letstalk.presentation.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentLoginBinding
import kg.turar.arykbaev.letstalk.presentation.BaseFragment


class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        ui.btnLogin.setOnClickListener {
            val email = ui.inputEmail.text.toString()
            val password = ui.inputPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        if (mAuth.currentUser?.isEmailVerified == true) {
                            findNavController().navigate(R.id.action_login_fragment_to_chat_fragment)
                        } else {
                            Toast.makeText(requireContext(), "Please, Verify your email", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(requireContext(), "Incorrect email or password", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(requireContext(), "Wrong!", Toast.LENGTH_SHORT).show()
            }
        }

        ui.tvRegistration.setOnClickListener {
            findNavController().navigate(R.id.action_login_fragment_to_registrationFragment)
        }
    }

    override fun performViewBinding(): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(layoutInflater)
    }
}