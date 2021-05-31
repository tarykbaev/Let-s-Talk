package kg.turar.arykbaev.letstalk.ui.person_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kg.turar.arykbaev.letstalk.databinding.FragmentPersonInfoBinding
import kg.turar.arykbaev.letstalk.domain.models.User
import kg.turar.arykbaev.letstalk.extension.setImageByUrl

class PersonInfoFragment : Fragment() {

    private lateinit var ui: FragmentPersonInfoBinding
    private val args: PersonInfoFragmentArgs by navArgs()
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = args.user
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ui = FragmentPersonInfoBinding.inflate(layoutInflater)
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        ui.imgPerson.setImageByUrl(user.image_url)
        ui.tvName.text = user.name
        ui.tvDepartment.text = user.department
        ui.tvYear.text = user.grade
        ui.tvCountry.text = user.from
        ui.tvNativeLan.text = user.nativeLang
        ui.tvLearnLan.text = user.learningLang

        ui.fabSend.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}