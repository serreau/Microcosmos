package sero.com.microcosmos.view.detailjob.bottomsheet.listoffer

import android.view.View
import androidx.core.os.postDelayed
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment__detail_job.view.*
import kotlinx.android.synthetic.main.fragment_list_offer.view.*
import sero.com.microcosmos.R
import sero.com.microcosmos.data.remote.response.GetOfferResponse
import sero.com.microcosmos.utils.EMPTY_OFFER_LIST_DELAY
import sero.com.microcosmos.view.detailjob.DetailJobFragment
import sero.com.microcosmos.view.detailjob.bottomsheet.CustomBottomSheet

class ListOfferBottomSheet(
    val parent: DetailJobFragment,
    listOffer: List<GetOfferResponse>) : BottomSheetBehavior<View>(), CustomBottomSheet {

    val view = parent.view!!
    val context = parent.context!!
    override var bottomSheet = from(view.listOfferBottomSheet).apply {
        state = STATE_HIDDEN
        if(listOffer.isEmpty())
            addBottomSheetCallback(ListOfferBottomSheetCallBack())
    }

    init {
        with(view) {
            listOfferBottomSheet.visibility = View.VISIBLE
            if(listOffer.isEmpty())
                listOfferBottomSheet.listOfferWording.text = context.getString(R.string.activity_create_offer__empty_list_wording)
            listOfferRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ListOfferAdapter(this@ListOfferBottomSheet.parent, listOffer)
            }
        }
    }

    inner class ListOfferBottomSheetCallBack : BottomSheetBehavior.BottomSheetCallback(){
        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            when(newState){
                STATE_EXPANDED -> android.os.Handler().postDelayed(EMPTY_OFFER_LIST_DELAY){
                    this@ListOfferBottomSheet.bottomSheet.state = STATE_HIDDEN
                }
            }
        }
    }
}