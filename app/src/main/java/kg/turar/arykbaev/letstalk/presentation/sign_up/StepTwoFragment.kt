package kg.turar.arykbaev.letstalk.presentation.sign_up


import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentStepTwoBinding
import kg.turar.arykbaev.letstalk.presentation.base.BaseFragment

class StepTwoFragment : BaseFragment<FragmentStepTwoBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        ui.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_stepTwoFragment_to_stepThreeFragment)
        }
    }

    override fun performViewBinding() = FragmentStepTwoBinding.inflate(layoutInflater)
}