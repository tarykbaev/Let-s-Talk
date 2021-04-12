package kg.turar.arykbaev.letstalk.presentation.sign_up

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentStepOneBinding
import kg.turar.arykbaev.letstalk.presentation.base.BaseFragment

class StepOneFragment : BaseFragment<FragmentStepOneBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        ui.btnSignUp.setOnClickListener {
//            val email = ui.inputEmail.text.toString()
//            val password = ui.inputPassword.text.toString()
//
//            mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
//                mAuth.currentUser.sendEmailVerification().addOnSuccessListener {
//
//                }
//            }

            findNavController().navigate(R.id.action_registrationFragment_to_stepTwoFragment)
        }
    }

    override fun performViewBinding() = FragmentStepOneBinding.inflate(layoutInflater)
}