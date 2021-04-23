package kg.turar.arykbaev.letstalk.ui.sign_up


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import kg.turar.arykbaev.letstalk.App
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentStepTwoBinding
import kg.turar.arykbaev.letstalk.model.Department
import kg.turar.arykbaev.letstalk.model.Grade
import kg.turar.arykbaev.letstalk.ui.base.BaseFragment
import kg.turar.arykbaev.letstalk.ui.search_activity.Option
import kg.turar.arykbaev.letstalk.ui.search_activity.SearchActivity
import org.parceler.Parcels


class StepTwoFragment : BaseFragment<FragmentStepTwoBinding, SignUpVM>(SignUpVM::class.java) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity?.application as App).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val selectedItem = Parcels.unwrap<Any>(data?.getParcelableExtra(SearchActivity.SELECTED_ITEM))
        data?.let {
            when(selectedItem) {
                is Department -> ui.inputDepartment.setText(selectedItem.description)
                is Grade -> ui.inputClass.setText(selectedItem.description)
            }
        }
    }

    private fun setupViews() {
        val department = listOf(Department("Computer eng"), Department("Chemistry"))
        ui.inputDepartment.setOnClickListener {
            SearchActivity.show(this, department.map { Option(it, it.description) })
        }
        val grades = listOf(Grade("Preparation"), Grade("I"), Grade("II"), Grade("III"), Grade("IV"))
        ui.inputClass.setOnClickListener {
            SearchActivity.show(this, grades.map { Option(it, it.description) })
        }
        ui.btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_stepTwoFragment_to_stepThreeFragment)
        }
    }

    override fun performViewBinding() = FragmentStepTwoBinding.inflate(layoutInflater)
}
