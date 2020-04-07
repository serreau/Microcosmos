package sero.com.microcosmos.data.remote.request.body

import com.google.gson.annotations.SerializedName

class OfferCreateBody (@SerializedName("owner") var owner : String,
                       @SerializedName("jobId") var jobId : String,
                       @SerializedName("price") var name : Int)