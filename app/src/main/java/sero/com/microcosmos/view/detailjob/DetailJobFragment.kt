package sero.com.microcosmos.view.detailjob

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment__detail_job.*
import sero.com.microcosmos.R
import sero.com.microcosmos.data.remote.response.JobGetResponse
import sero.com.microcosmos.utils.z69_200

class DetailJobFragment : Fragment() {
    companion object{
        const val JOB_ID="JOB_ID"
    }

    private val viewmodel: DetailJobViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment__detail_job, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString(JOB_ID, "Title")
        val job = viewmodel.get(id)
        bind(job)
        validate.setOnClickListener {}
    }

    private fun bind(job: JobGetResponse?) {
        with(job){
            context?.let { viewmodel.setImage(it, this?.ownerMail, image) }
            title.text = job?.name
            owner.text = job?.ownerFirstname
            date.text = job?.date?.let { z69_200(it) }
        }
    }
}