package kg.turar.arykbaev.letstalk.ui.sign_up


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.theartofdev.edmodo.cropper.CropImage
import kg.turar.arykbaev.letstalk.App
import kg.turar.arykbaev.letstalk.databinding.FragmentStepTwoBinding
import kg.turar.arykbaev.letstalk.domain.Gender
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
        setUser()
        setupViews()
    }

    private fun setUser() {
        vm.user = args.user
        println("+++++++++++++++++++++++++=")
        println(vm.user)
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
        if (vm.user.uri != null) ui.imgPerson.setImageURI(vm.user.uri)

        ui.inputDepartment.setOnClickListener {
            SearchActivity.show(this, vm.getDepartments().map { Option(Department(it), it) })
        }
        ui.inputClass.setOnClickListener {
            SearchActivity.show(this, vm.getGrades().map { Option(Grade(it), it) })
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
                ui.rbFemale.id -> setGender(Gender.FEMALE)
                ui.rbMale.id -> setGender(Gender.MALE)
            }
            toggleButtonState()
        }
    }

    private fun setGender(gender: Gender) {
        vm.user.gender = gender.gender
    }

    private fun toggleButtonState() {
        ui.btnNext.isEnabled = vm.allDatesValid()
    }

    override fun performViewBinding() = FragmentStepTwoBinding.inflate(layoutInflater)
}
