package kg.turar.arykbaev.letstalk.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.firebase.auth.FirebaseAuth

abstract class BaseFragment<DataBinding : ViewBinding> : Fragment() {

    protected lateinit var mAuth: FirebaseAuth
    protected lateinit var ui: DataBinding

    abstract fun performViewBinding(): DataBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ui = performViewBinding()
        return ui.root
    }
}