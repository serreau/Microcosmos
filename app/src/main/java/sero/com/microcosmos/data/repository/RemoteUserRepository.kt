package sero.com.microcosmos.data.repository

import okhttp3.MultipartBody
import okhttp3.RequestBody
import sero.com.microcosmos.data.remote.UserRemote
import sero.com.microcosmos.data.remote.request.body.UserExistBody
import sero.com.microcosmos.data.remote.request.body.UserInsertBody
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
}