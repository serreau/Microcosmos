package sero.com.microcosmos.view.createJob

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_create_job.*
import sero.com.microcosmos.R
import sero.com.microcosmos.utils.getValue
import sero.com.microcosmos.utils.toastIt

class CreateJobFragment : Fragment() {
    private val viewmodel: CreateJobViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create_job, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomNavigationView.setOnNavigationItemSelectedListener(activity as BottomNavigationView.OnNavigationItemSelectedListener)
        createJobButton.setOnClickListener {
            val success = viewmodel.createJob(context, getValue( name )) ?: false
            if(success) {
                toastIt(context, getString(R.string.activity_create_job__create_job_success))
                findNavController().navigate(R.id.searchFragment)
            }
            else
                toastIt(context, getString(R.string.activity_create_job__create_job_error))
        }
    }

}