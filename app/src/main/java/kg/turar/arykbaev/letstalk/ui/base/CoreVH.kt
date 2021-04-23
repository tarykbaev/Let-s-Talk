package kg.turar.arykbaev.letstalk.ui.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class CoreVH<T, DataBinding : ViewBinding>(ui: DataBinding) : RecyclerView.ViewHolder(ui.root) {
    abstract fun onBind(item: T)
}
