package sero.com.microcosmos.view.detailjob

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment__detail_job.*
import kotlinx.android.synthetic.main.fragment_create_offer.*
import sero.com.microcosmos.R
import sero.com.microcosmos.data.remote.response.JobGetResponse
import sero.com.microcosmos.utils.*

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
        val id = arguments?.getString(JOB_ID, "Title") ?: return
        bind(viewmodel.get(id))
        var bottomSheetBehavior = BottomSheetBehavior.from(bottomsheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheetBehavior.addBottomSheetCallback(OfferBottomSheetCallBack())
        createOffer.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        send.setOnClickListener{
            context?.let { hideSoftKeyboard(it, price) }
                .let{viewmodel.newOffer(context!!, id, getValue(price).toInt())}
                .let { findNavController().navigate(R.id.viewPagerFragment) }
            toastIt(context, getString(R.string.activity_create_offer__offer_success))
        }
    }

    private fun bind(job: JobGetResponse?) {
        if (job != null) context?.let { viewmodel.setImage(it, job.ownerMail, image) }
        title.text = job?.name
        owner.text = job?.ownerFirstname
        date.text = job?.date?.let { z69_200(it) }
    }

    inner class OfferBottomSheetCallBack : BottomSheetBehavior.BottomSheetCallback(){
        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            when(newState){
                BottomSheetBehavior.STATE_EXPANDED -> context?.let { showSoftKeyboard(it, price) }
                BottomSheetBehavior.STATE_HIDDEN -> context?.let { hideSoftKeyboard(it, price) }
            }
        }
    }
}