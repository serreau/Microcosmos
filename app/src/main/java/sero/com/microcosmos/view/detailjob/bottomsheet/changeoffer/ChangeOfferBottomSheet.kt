package sero.com.microcosmos.view.detailjob.bottomsheet.changeoffer

import android.view.View
import androidx.core.os.postDelayed
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment__detail_job.view.*
import kotlinx.android.synthetic.main.fragment_change_offer.view.*
import sero.com.microcosmos.data.remote.response.GetOfferResponse
import sero.com.microcosmos.utils.EMPTY_OFFER_LIST_DELAY
import sero.com.microcosmos.view.detailjob.DetailJobFragment
import sero.com.microcosmos.view.detailjob.bottomsheet.CustomBottomSheet

class ChangeOfferBottomSheet(
    val parent: DetailJobFragment,
    jobOffer: GetOfferResponse
) : BottomSheetBehavior<View>(),
    CustomBottomSheet {
    val view = parent.view!!
    val context = parent.context!!
    override var bottomSheet = from(view.changeOfferBottomSheet).apply {
        state = STATE_HIDDEN
        addBottomSheetCallback(ChangeOfferBottomSheetCallBack())
    }

    init {
        with(view.changeOfferBottomSheet){
            visibility = View.VISIBLE
            price.text = jobOffer.price.toString()
        }
    }

    inner class ChangeOfferBottomSheetCallBack : BottomSheetBehavior.BottomSheetCallback(){
        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            when(newState){
                STATE_EXPANDED -> android.os.Handler().postDelayed(EMPTY_OFFER_LIST_DELAY){
                    this@ChangeOfferBottomSheet.bottomSheet.state = STATE_HIDDEN
                }
            }
        }
    }
}