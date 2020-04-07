package sero.com.microcosmos.view.detailjob

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import sero.com.microcosmos.data.remote.response.JobGetResponse
import sero.com.microcosmos.data.repository.LocalUserRepository
import sero.com.microcosmos.data.repository.RemoteJobRepository
import sero.com.microcosmos.data.repository.RemoteOfferRepository
import sero.com.microcosmos.data.repository.RemoteUserRepository

class DetailJobViewModel : ViewModel() {
    private val remoteJobRepository : RemoteJobRepository = RemoteJobRepository()
    private val remoteUserRepository : RemoteUserRepository = RemoteUserRepository()
    private val remoteOfferRepository : RemoteOfferRepository = RemoteOfferRepository()
    private val localUserRepository : LocalUserRepository = LocalUserRepository()

    fun get(id : String?) : JobGetResponse? = runBlocking {
        id?.let { remoteJobRepository.get(it) }
    }

    fun setImage(
        context: Context,
        owner: String,
        image: ImageView
    ) = remoteUserRepository.setImage(context, owner, image)

    fun newOffer(context: Context, jobId: String, price: Int) = runBlocking {
        if(localUserRepository.stillConnected(context)){
            val username = localUserRepository.getUserName(context)
            remoteOfferRepository.insert(username, jobId, price)
        }
    }
}