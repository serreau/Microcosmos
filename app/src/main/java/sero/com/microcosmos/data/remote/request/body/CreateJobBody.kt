package sero.com.microcosmos.data.remote.request.body

import com.google.gson.annotations.SerializedName

class CreateJobBody (@SerializedName("owner") var owner : String,
                     @SerializedName("name") var name : String)