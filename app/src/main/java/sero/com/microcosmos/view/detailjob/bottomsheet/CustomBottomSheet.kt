package sero.com.microcosmos.view.detailjob.bottomsheet

import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior

interface CustomBottomSheet {
    var bottomSheet : BottomSheetBehavior<View>
    fun get() : BottomSheetBehavior<View> = bottomSheet
    fun set(bottomSheet: BottomSheetBehavior<View>) {
        this.bottomSheet = bottomSheet
    }
}