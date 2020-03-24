package sero.com.microcosmos.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.item__search_recycler_view.view.*
import sero.com.microcosmos.R
import sero.com.microcosmos.data.remote.response.JobGetResponse

class SearchFragment : Fragment() {
    private val model: SearchViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationView.setNavigationItemSelectedListener(activity as NavigationView.OnNavigationItemSelectedListener)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = SearchAdapter()
        }
    }

    inner class SearchAdapter(private val list : List<JobGetResponse> = model.get() ) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
            val card = LayoutInflater.from(parent.context)
                .inflate(R.layout.item__search_recycler_view, parent, false) as CardView
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