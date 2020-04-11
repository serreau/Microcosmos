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
import sero.com.microcosmos.data.remote.response.GetJobResponse
import sero.com.microcosmos.data.remote.response.GetOfferResponse
import sero.com.microcosmos.utils.getValue
import sero.com.microcosmos.utils.toastIt
import sero.com.microcosmos.utils.z69_200
import sero.com.microcosmos.view.detailjob.bottomsheet.changeoffer.ChangeOfferBottomSheet
import sero.com.microcosmos.view.detailjob.bottomsheet.listoffer.ListOfferBottomSheet
import sero.com.microcosmos.view.detailjob.bottomsheet.newoffer.NewOfferBottomSheet

class DetailJobFragment : Fragment() {
    companion object{
        const val JOB_ID="JOB_ID"
    }

    val viewmodel: DetailJobViewModel by viewModels()
    private lateinit var jobId : String
    private lateinit var jobUserId : String
    private lateinit var jobOffer : List<GetOfferResponse>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment__detail_job, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jobId = arguments?.getString(JOB_ID, "Title") ?: return
        viewmodel.get(jobId)?.let {
            bind(it)
        }
        jobOffer = viewmodel.getOffer(context!!, jobId)

        if(viewmodel.isCurrentUserOwner(context!!, jobUserId))
            initViewAsOwner()
        else if (jobOffer.isNotEmpty())
            initViewAsApplicant()
        else
            initViewAsGuest()
    }

    private fun initViewAsOwner(){
        validate.apply {
            text = getString(R.string.activity_detail__job_show_list_offer)
            val bottomSheet = ListOfferBottomSheet(this@DetailJobFragment, viewmodel.getListOffer(jobId))
            setOnClickListener {
                bottomSheet.get().state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }

    private fun initViewAsApplicant(){
        validate.apply {
            text = getString(R.string.activity_detail__job_show_change_offer)
            val bottomSheet = ChangeOfferBottomSheet(this@DetailJobFragment, jobOffer.first() )
            setOnClickListener {
                bottomSheet.get().state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }

    private fun initViewAsGuest(){
        validate.apply {
            text = getString(R.string.activity_detail__job_make_offer)
            val bottomSheet = NewOfferBottomSheet(this@DetailJobFragment )
            setOnClickListener {
                bottomSheet.get().state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }

    private fun bind(job: GetJobResponse) {
        context?.let { viewmodel.setImage(it, job.ownerMail, image) }
        title.text = job.name
        owner.text = job.ownerFirstname
        date.text = z69_200(job.date)
        jobUserId = job.ownerMail
    }

    fun newOffer() {
        jobId.let { viewmodel.newOffer(context!!, it, getValue(price).toInt()) }
        toastIt(context, context!!.getString(R.string.activity_create_offer__offer_success))
        findNavController().navigate(R.id.viewPagerFragment)
    }
}