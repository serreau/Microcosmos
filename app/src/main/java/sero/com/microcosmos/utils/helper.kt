package sero.com.microcosmos.utils

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.text.Editable
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val retrofit : Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun getValue(textInputEditText: TextInputEditText) = textInputEditText?.let { it.text.toString() }

fun getValue(editable : Editable?) = editable.let { it.toString() }

fun toastIt(context : Context?, text : String) = Toast.makeText(context, text, LENGTH_SHORT).show()

fun iso8101(date : String) : LocalDateTime =
    LocalDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(date))

fun z69_200(date : String) : String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return iso8101(date).format(formatter)
}

fun Uri.getPathString(context: Context): String {
    var path = ""
    context.contentResolver.query(this, arrayOf(MediaStore.Images.Media.DATA), null, null, null)
        ?.apply {
        moveToFirst()
        path = getString(0)
        close()
    }
    return path
}