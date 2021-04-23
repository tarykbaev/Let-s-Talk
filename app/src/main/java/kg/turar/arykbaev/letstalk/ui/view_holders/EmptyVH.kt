package kg.turar.arykbaev.letstalk.ui.view_holders

import android.view.LayoutInflater
import android.view.ViewGroup
import kg.turar.arykbaev.letstalk.databinding.ItemEmptyBinding
import kg.turar.arykbaev.letstalk.ui.base.CoreVH


class EmptyVH(private val ui: ItemEmptyBinding) :
    CoreVH<Int, ItemEmptyBinding>(ui) {

    companion object {
        fun create(parent: ViewGroup): EmptyVH {
            val view =
                ItemEmptyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return EmptyVH(view)
        }
    }

    override fun onBind(item: Int) {
        ui.textEmptyList.text = ui.root.context.getString(item)
    }
}