package kg.turar.arykbaev.letstalk.ui.sign_up


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.theartofdev.edmodo.cropper.CropImage
import kg.turar.arykbaev.letstalk.App
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.FragmentStepTwoBinding
import kg.turar.arykbaev.letstalk.domain.Gender
import kg.turar.arykbaev.letstalk.domain.User
import kg.turar.arykbaev.letstalk.model.Department
import kg.turar.arykbaev.letstalk.model.Grade
import kg.turar.arykbaev.letstalk.ui.MainActivity
import kg.turar.arykbaev.letstalk.ui.base.BaseFragment
import kg.turar.arykbaev.letstalk.ui.search_activity.Option
import kg.turar.arykbaev.letstalk.ui.search_activity.SearchActivity
import org.parceler.Parcels


class StepTwoFragment : BaseFragment<FragmentStepTwoBinding, SignUpVM>(SignUpVM::class.java) {

    private val args: StepTwoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity?.application as App).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setUser()
    }

    private fun setUser() {
        vm.user = args.user
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val selectedItem = Parcels.unwrap<Any>(data?.getParcelableExtra(SearchActivity.SELECTED_ITEM))
        data?.let {
            when(selectedItem) {
                is Department -> setDepartment(selectedItem.description)
                is Grade -> setGrade(selectedItem.description)
            }
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val uri = CropImage.getActivityResult(data).uri
            vm.user.uri = uri
            ui.imgPerson.setImageURI(uri)
        }
        toggleButtonState()
    }

    private fun setDepartment(department: String) {
        ui.inputDepartment.setText(department)
        vm.user.department = department
    }

    private fun setGrade(grade: String) {
        ui.inputClass.setText(grade)
        vm.user.grade = grade
    }

    private fun setupViews() {
        val department = listOf(Department("Computer eng"), Department("Chemistry"))
        ui.inputDepartment.setOnClickListener {
            SearchActivity.show(this, department.map { Option(it, it.description) })
        }
        val grades = listOf(Grade("0"), Grade("I"), Grade("II"), Grade("III"), Grade("IV"))
        ui.inputClass.setOnClickListener {
            SearchActivity.show(this, grades.map { Option(it, it.description) })
        }
        ui.btnNext.setOnClickListener {
            navigateTo(StepTwoFragmentDirections.toStepThreeFragment(vm.user))
        }

        ui.imgPerson.setOnClickListener {
            CropImage.activity()
                .setAspectRatio(1, 1)
                .setRequestedSize(400, 400)
                .start((activity as MainActivity), this)
        }

        ui.rgGender.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                ui.rbFemale.id -> setGender(FEMALE)
                ui.rbMale.id -> setGender(MALE)
            }
            toggleButtonState()
        }
    }

    private fun setGender(gender: String) {
        vm.user.gender = gender
    }

    private fun toggleButtonState() {
        ui.btnNext.isEnabled = vm.allDatesValid()
    }

    override fun performViewBinding() = FragmentStepTwoBinding.inflate(layoutInflater)

    companion object {
        private const val FEMALE = "female"
        private const val MALE = "male"
    }
}
