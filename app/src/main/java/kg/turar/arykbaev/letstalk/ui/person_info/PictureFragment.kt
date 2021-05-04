package kg.turar.arykbaev.letstalk.ui.person_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kg.turar.arykbaev.letstalk.databinding.FragmentPictureBinding
import kg.turar.arykbaev.letstalk.domain.models.User
import kg.turar.arykbaev.letstalk.extension.setImageByUrl

class PictureFragment : Fragment() {

    private lateinit var ui: FragmentPictureBinding
    private val args: PictureFragmentArgs by navArgs()
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
        ui = FragmentPictureBinding.inflate(layoutInflater)
        return ui.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ui.imgProfile.setImageByUrl(user.image_url)
    }
}