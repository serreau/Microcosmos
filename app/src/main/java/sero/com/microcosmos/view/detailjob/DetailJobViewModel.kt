package sero.com.microcosmos.view.detailjob

import android.content.Context
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import sero.com.microcosmos.data.remote.response.GetJobResponse
import sero.com.microcosmos.data.remote.response.GetOfferResponse
import sero.com.microcosmos.data.repository.LocalUserRepository
import sero.com.microcosmos.data.repository.RemoteJobRepository
import sero.com.microcosmos.data.repository.RemoteOfferRepository
import sero.com.microcosmos.data.repository.RemoteUserRepository

class DetailJobViewModel : ViewModel() {
    private val remoteJobRepository : RemoteJobRepository = RemoteJobRepository()
    private val remoteUserRepository : RemoteUserRepository = RemoteUserRepository()
    private val remoteOfferRepository : RemoteOfferRepository = RemoteOfferRepository()
    private val localUserRepository : LocalUserRepository = LocalUserRepository()

    fun get(id : String?) : GetJobResponse? = runBlocking {
        id?.let { remoteJobRepository.get(it) }
    }

    fun setImage(
        context: Context,
        owner: String,
        image: ImageView
    ) = remoteUserRepository.setImage(context, owner, image)

    fun isCurrentUserOwner(context : Context, userId : String) = runBlocking{
        userId == localUserRepository.getUserId(context)
    }

    fun newOffer(context: Context, jobId: String, price: Int) = runBlocking {
        if(localUserRepository.stillConnected(context)){
            val userId = localUserRepository.getUserId(context)
            remoteOfferRepository.insert(userId, jobId, price)
        } else
            false
    }

    fun getListOffer(jobId : String) : List<GetOfferResponse> = runBlocking{
        remoteOfferRepository.getByJobId(jobId)
    }
}