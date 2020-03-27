package sero.com.microcosmos.data.remote.request.body

import com.google.gson.annotations.SerializedName

class UserInsertBody (@SerializedName("firstname") var firstname : String,
                      @SerializedName("lastname") var lastname : String,
                      @SerializedName("phone") var phone : String,
                      @SerializedName("mail") var mail : String,
                      @SerializedName("password") var password : String)