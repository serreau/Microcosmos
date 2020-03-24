package sero.com.microcosmos.view.main

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import sero.com.microcosmos.R
import sero.com.microcosmos.utils.ON_BACK_PRESSED_DELAY
import sero.com.microcosmos.utils.toastIt

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val model : MainViewModel by viewModels()
    private var isBackStackActive = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.goto_create_job -> {
                nav_host.findNavController().navigate(R.id.createJobFragment)
            }
            R.id.goto_search_job -> {
                nav_host.findNavController().navigate(R.id.searchFragment)
            }
            R.id.goto_my_job -> { }
            R.id.goto_history -> { }
            R.id.goto_profile -> { }
            R.id.goto_setting -> { }
            R.id.goto_logout -> {
                nav_host.findNavController().navigate(R.id.loginFragment)
                model.disconnect(this)
                toastIt(this, getString(R.string.activity_login__logout_success))
            }
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
