package sero.com.microcosmos.data.remote.request.body

import com.google.gson.annotations.SerializedName
import sero.com.microcosmos.utils.State
import java.time.LocalDateTime

class CreateJobBody (@SerializedName("owner") var owner : String,
                     @SerializedName("name") var name : String)