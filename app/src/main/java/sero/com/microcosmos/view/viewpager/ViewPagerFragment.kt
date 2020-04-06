package sero.com.microcosmos.view.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.android.synthetic.main.fragment__view_pager.*
import sero.com.microcosmos.R
import sero.com.microcosmos.utils.State
import sero.com.microcosmos.view.search.SearchFragment

class ViewPagerFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(R.layout.fragment__view_pager, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPager.adapter = ViewPagerAdapter(this)
    }

    private class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount() = State.values().size
        override fun createFragment(position: Int) = SearchFragment(State.values().get(position))
    }
}