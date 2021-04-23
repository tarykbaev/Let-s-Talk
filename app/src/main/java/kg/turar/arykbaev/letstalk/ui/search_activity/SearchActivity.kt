package kg.turar.arykbaev.letstalk.ui.search_activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kg.turar.arykbaev.letstalk.R
import kg.turar.arykbaev.letstalk.databinding.ActivitySearchBinding
import kg.turar.arykbaev.letstalk.extension.gone
import kg.turar.arykbaev.letstalk.model.Grade
import kg.turar.arykbaev.letstalk.ui.search_activity.adapter.SearchAdapter
import org.parceler.Parcels


class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener, SearchAdapter.Listener {

    private lateinit var ui: ActivitySearchBinding
    private val searchListAdapter = SearchAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(ui.root)
        setUpToolbar()
        setupView()
    }

    private fun setUpToolbar() {
        ui.toolbarSearch.apply {
            setNavigationIcon(R.drawable.ic_close)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            setSupportActionBar(this)
        }
    }

    private fun setupView() {

        val items = Parcels.unwrap<List<Option<*>>>(intent.getParcelableExtra(EXTRA_ITEMS))
        searchListAdapter.updateList(items)

        ui.searchList.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = searchListAdapter
        }

        val searchClose: ImageView = ui.searchView.findViewById(androidx.appcompat.R.id.search_close_btn)
        searchClose.setImageResource(R.drawable.ic_close)

        ui.searchView.apply {
            setOnQueryTextListener(this@SearchActivity)
            onActionViewExpanded()
            if (items.first().item is Grade) {
                title = ""
                gone()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        setResult(Activity.RESULT_CANCELED)
        onBackPressed()
        finish()
        return true
    }

    override fun onSearchItemClick(item: Any) {
        setResult(Activity.RESULT_OK, Intent().apply {
            putExtra(SELECTED_ITEM, Parcels.wrap(item))
        })
        finish()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchListAdapter.filter.filter(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchListAdapter.filter.filter(newText)
        return false
    }

    companion object {
        private const val REQUEST_CODE = 1567
        private const val EXTRA_ITEMS = "EXTRA_ITEMS"
        const val SELECTED_ITEM = "SELECTED_ITEM"

        fun show(fragment: Fragment, list: List<Option<*>>? = null) {
            val intent = Intent(fragment.requireActivity(), SearchActivity::class.java)
            intent.putExtra(EXTRA_ITEMS, Parcels.wrap(list))
            fragment.startActivityForResult(intent, REQUEST_CODE)
        }
    }
}