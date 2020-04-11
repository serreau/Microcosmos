package sero.com.microcosmos.view.detailjob.bottomsheet.listoffer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item__list_offer_recycler_view.view.*
import kotlinx.android.synthetic.main.item__list_offer_recycler_view.view.date
import kotlinx.android.synthetic.main.item__list_offer_recycler_view.view.image
import kotlinx.android.synthetic.main.item__list_offer_recycler_view.view.owner
import sero.com.microcosmos.R
import sero.com.microcosmos.data.remote.response.GetOfferResponse
import sero.com.microcosmos.utils.z69_200
import sero.com.microcosmos.view.detailjob.DetailJobFragment

class ListOfferAdapter(
    val parent: DetailJobFragment,
    val listOffer: List<GetOfferResponse>
) : RecyclerView.Adapter<ListOfferAdapter.ListOfferViewHolder>() {
    val viewmodel = parent.viewmodel
    val context = parent.context!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListOfferViewHolder {
        val item= LayoutInflater.from(parent.context)
            .inflate(R.layout.item__list_offer_recycler_view, parent, false) as ConstraintLayout
        return ListOfferViewHolder(item)
    }
    override fun onBindViewHolder(holder: ListOfferViewHolder, position: Int){
        holder.bind(listOffer[position])
    }
    override fun getItemCount() = listOffer.size

    inner class ListOfferViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(offer : GetOfferResponse){
            context.let { viewmodel.setImage(it, offer.ownerMail, itemView.image) }
            itemView.owner.text = offer.ownerFirstname
            itemView.date.text = z69_200(offer.date)
            itemView.price.text = offer.price.toString()
        }
    }
}