package sero.com.microcosmos.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_search.*
import sero.com.microcosmos.R

class SearchFragment : Fragment() {
    private val model: SearchViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationView.setNavigationItemSelectedListener(activity as NavigationView.OnNavigationItemSelectedListener)
    }
}