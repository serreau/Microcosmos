package sero.com.microcosmos.view.detailjob.bottomsheet.listoffer

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment__detail_job.view.*
import kotlinx.android.synthetic.main.fragment_list_offer.view.*
import sero.com.microcosmos.data.remote.response.GetOfferResponse
import sero.com.microcosmos.view.detailjob.DetailJobFragment
import sero.com.microcosmos.view.detailjob.bottomsheet.CustomBottomSheet

class ListOfferBottomSheet(
    val parent: DetailJobFragment,
    listOffer: List<GetOfferResponse>
) : BottomSheetBehavior<View>(),
    CustomBottomSheet {
    val view = parent.view!!
    val context = parent.context!!
    override var bottomSheet = from(view.listOfferBottomSheet).apply {
        state = STATE_HIDDEN
    }

    init {
        with(view) {
            listOfferBottomSheet.visibility = View.VISIBLE
            listOfferRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ListOfferAdapter(this@ListOfferBottomSheet.parent, listOffer)
            }
        }
    }
}