package kg.turar.arykbaev.letstalk.ui.search_activity.adapter

import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import kg.turar.arykbaev.letstalk.ui.search_activity.Option
import kg.turar.arykbaev.letstalk.ui.view_holders.EmptyVH
import kg.turar.arykbaev.letstalk.ui.view_holders.ItemViewType
import kg.turar.arykbaev.letstalk.ui.view_holders.ItemViewType.EMPTY
import java.util.*
import kotlin.collections.ArrayList

class SearchAdapter(private val listener: Listener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    Filterable {

    private var filteredList: MutableList<Option<*>> = ArrayList()
    private var inputCharLength: Int = 0
    private lateinit var list: MutableList<Option<*>>

    fun updateList(items: List<Option<*>>) {
        filteredList.clear()
        filteredList.addAll(items.toMutableList())
        list = filteredList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        EMPTY -> EmptyVH.create(parent)
        else -> SearchVH.create(parent, listener)
    }

    override fun getItemCount() = when (filteredList.isNullOrEmpty()) {
        true -> ItemViewType.NORMAL
        else -> filteredList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, index: Int) {
        when (holder) {
            is SearchVH -> holder.onBind(filteredList[index].apply{highlightChars = inputCharLength})
        }
    }

    override fun getItemViewType(position: Int) = when (filteredList.isNullOrEmpty()) {
        true -> EMPTY
        else -> ItemViewType.NORMAL
    }

    interface Listener {
        fun onSearchItemClick(item: Any)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results: FilterResults = FilterResults()

                if(constraint == null || constraint.isEmpty()) {
                    inputCharLength = 0
                    results.values = list
                    results.count = list.size
                } else {
                    inputCharLength = constraint.length
                    val newList = list.filter{it.description.toLowerCase(Locale.ROOT).startsWith(constraint.toString().toLowerCase())}
                    results.values = newList.toMutableList()
                    results.count = newList.size
                }
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as MutableList<Option<*>>
                notifyDataSetChanged()
            }

        }
    }
}