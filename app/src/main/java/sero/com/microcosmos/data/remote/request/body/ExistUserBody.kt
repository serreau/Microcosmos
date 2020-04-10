package sero.com.microcosmos.data.remote.request.body

import com.google.gson.annotations.SerializedName

class ExistUserBody (@SerializedName("mail") var login : String,
                     @SerializedName("password") var password : String)