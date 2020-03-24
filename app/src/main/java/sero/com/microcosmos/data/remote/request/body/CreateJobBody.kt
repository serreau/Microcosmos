package sero.com.microcosmos.data.remote.request.body

import com.google.gson.annotations.SerializedName
import sero.com.microcosmos.utils.State

class CreateJobBody (@SerializedName("owner") var owner : String,
                    @SerializedName("name") var name : String,
                     @SerializedName("state") var state : String = State.TODO.name)