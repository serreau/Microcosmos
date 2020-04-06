package sero.com.microcosmos.view.main

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import sero.com.microcosmos.R
import sero.com.microcosmos.utils.ON_BACK_PRESSED_DELAY
import sero.com.microcosmos.utils.toastIt

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private val viewmodel : MainViewModel by viewModels()
    private var isBackStackActive = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.goto_profile -> {
                    if(viewmodel.stillConnected(this))
                        nav_host.findNavController().navigate(R.id.profileFragment)
                    else
                        nav_host.findNavController().navigate(R.id.loginFragment)
                }
                R.id.goto_show_chat -> {}
                R.id.goto_new_job -> { nav_host.findNavController().navigate(R.id.createJobFragment) }
                R.id.goto_board -> { nav_host.findNavController().navigate(R.id.viewPagerFragment) }
                R.id.goto_show_notification -> {}
                else -> {}
            }
        return true
    }

    override fun onBackPressed() {
        if(isBackStackActive)
            finish()
        isBackStackActive = true
        toastIt(this, getString(R.string.activity_main__exit))
        Handler().postDelayed(ON_BACK_PRESSED_DELAY) { isBackStackActive = false }
    }
}
