package sero.com.microcosmos.view.detailjob.bottomsheet.newoffer

import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment__detail_job.view.*
import kotlinx.android.synthetic.main.fragment_create_offer.view.*
import sero.com.microcosmos.utils.hideSoftKeyboard
import sero.com.microcosmos.utils.showSoftKeyboard
import sero.com.microcosmos.view.detailjob.DetailJobFragment
import sero.com.microcosmos.view.detailjob.bottomsheet.CustomBottomSheet

class NewOfferBottomSheet(val parent : DetailJobFragment) : BottomSheetBehavior<View>(),
    CustomBottomSheet {
    val view = parent.view!!
    val context = parent.context!!
    override var bottomSheet = from(view.newOfferBottomSheet).apply {
        state = STATE_HIDDEN
        addBottomSheetCallback(NewOfferBottomSheetCallBack())
    }

    init {
        with(view){
            newOfferBottomSheet.visibility = View.VISIBLE
            send.setOnClickListener{
                context.let{
                    hideSoftKeyboard(it, view.price)
                }.let{
                    this@NewOfferBottomSheet.parent.newOffer()
                }
            }
        }
    }

    inner class NewOfferBottomSheetCallBack() : BottomSheetBehavior.BottomSheetCallback(){
        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            when(newState){
                STATE_EXPANDED -> showSoftKeyboard(context, view.price)
                STATE_HIDDEN -> hideSoftKeyboard(context, view.price)
            }
        }
    }
}