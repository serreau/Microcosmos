package sero.com.microcosmos.data.repository

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import okhttp3.MultipartBody
import okhttp3.RequestBody
import sero.com.microcosmos.R
import sero.com.microcosmos.data.remote.UserRemote
import sero.com.microcosmos.data.remote.request.body.UserExistBody
import sero.com.microcosmos.data.remote.request.body.UserInsertBody
import sero.com.microcosmos.utils.BASE_URL
import sero.com.microcosmos.utils.retrofit
import java.io.File


class RemoteUserRepository {
    private val remote = retrofit.create(UserRemote::class.java)

    suspend fun connect(login : String, password : String) =
        remote.exist(UserExistBody(login, password)).success

    suspend fun insert(
        firstname: String,
        lastname: String,
        phone: String,
        mail: String,
        password: String,
        image: File
    ) : Boolean {
        val requestFile = RequestBody.create(MultipartBody.FORM, image)
        val requestName = RequestBody.create(MultipartBody.FORM, mail)
        val requestBody = MultipartBody.Part.createFormData("image", image.name, requestFile)

        return remote.insert(UserInsertBody(firstname, lastname, phone, mail, password)).success
                && remote.insert(requestName, requestBody).success
    }

    fun setImage(context: Context, owner: String?, image: ImageView) {
        Glide.with(context!!)
            .load(BASE_URL+"/user/image/"+owner)
            .error(R.mipmap.bee)
            .placeholder(R.mipmap.bee)
            .into(image)
    }
}