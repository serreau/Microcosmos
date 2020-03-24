package sero.com.microcosmos.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.item__search_recycler_view.view.*
import sero.com.microcosmos.R
import sero.com.microcosmos.data.remote.response.JobGetResponse
import sero.com.microcosmos.utils.getValue

class SearchFragment : Fragment() {
    private val model: SearchViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationView.setNavigationItemSelectedListener(activity as NavigationView.OnNavigationItemSelectedListener)
        val searchAdapter = SearchAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = searchAdapter
        }
        search.doAfterTextChanged { editable ->
            searchAdapter.refresh(model.getSearch(getValue(editable)))
        }
    }

    inner class SearchAdapter(var list : List<JobGetResponse> = model.getSearch() ) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(){
        fun refresh(list : List<JobGetResponse>) {
            this.list = list
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
            val card = LayoutInflater.from(parent.context)
                .inflate(R.layout.item__search_recycler_view, parent, false) as ConstraintLayout
            return SearchViewHolder(card)
        }

        override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
            holder.itemView.owner.text = list[position].owner.toString()
            holder.itemView.name.text = list[position].name.toString()
        }

        override fun getItemCount() = list.size

        inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    }
}