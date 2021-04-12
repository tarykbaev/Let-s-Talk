package kg.turar.arykbaev.letstalk.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<DataBinding: ViewBinding> : AppCompatActivity() {

    protected lateinit var ui: DataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = performBinding()
        setContentView(ui.root)
    }

    abstract fun performBinding(): DataBinding
}