package kg.turar.arykbaev.letstalk.presentation.search.adapter

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.ItemSearchBinding
import kg.turar.arykbaev.letstalk.presentation.base.CoreVH
import kg.turar.arykbaev.letstalk.presentation.search.Option


class SearchVH(
    private val ui: ItemSearchBinding,
    private val listener: SearchAdapter.Listener
) : CoreVH<Option<*>, ItemSearchBinding>(ui) {

    private lateinit var option: Option<*>

    override fun onBind(item: Option<*>) {
        option = item
        ui.searchItem.apply {
            text = when {
                option.highlightChars < 1 -> option.description
                else -> {
                    val spannableString = SpannableString(item.description)
                    val foregroundColorSpan =
                        ForegroundColorSpan(resources.getColor(R.color.primary))
                    spannableString.setSpan(
                        foregroundColorSpan,
                        0,
                        option.highlightChars,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    spannableString
                }
            }
            setOnClickListener { listener.onSearchItemClick(option.item) }
        }
    }

    companion object {
        fun create(parent: ViewGroup, listener: SearchAdapter.Listener): SearchVH {
            val view = ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SearchVH(view, listener = listener)
        }
    }
}