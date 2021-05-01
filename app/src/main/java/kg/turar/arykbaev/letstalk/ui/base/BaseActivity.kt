package kg.turar.arykbaev.letstalk.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import javax.inject.Inject

abstract class BaseActivity<DataBinding: ViewBinding, ViewModel : BaseVM>
    (private var viewModelClass: Class<ViewModel>): AppCompatActivity() {

    protected lateinit var ui: DataBinding
    protected lateinit var vm: ViewModel
    @Inject protected lateinit var vmFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = performBinding()
        setContentView(ui.root)
        vm = ViewModelProvider(this, vmFactory).get(viewModelClass)
    }

    abstract fun performBinding(): DataBinding
}