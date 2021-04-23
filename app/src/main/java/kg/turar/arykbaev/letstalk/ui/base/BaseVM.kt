package kg.turar.arykbaev.letstalk.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.turar.arykbaev.letstalk.domain.Event

open class BaseVM : ViewModel() {
    var event: MutableLiveData<Event> = MutableLiveData()

    fun showLoading() {}

    fun hideLoading() {}
}