package sero.com.microcosmos.utils

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.google.android.material.textfield.TextInputEditText
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.apache.commons.io.IOUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

//RETROFIT

val logging =  HttpLoggingInterceptor().apply { level=HttpLoggingInterceptor.Level.BODY }
val httpClient =  OkHttpClient.Builder().apply { addInterceptor(logging) }
val retrofit : Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(httpClient.build())
    .addConverterFactory(GsonConverterFactory.create())
    .build()

//SHORTCUT

fun getValue(textInputEditText: TextInputEditText) = textInputEditText.let { it.text.toString() }

fun getValue(editable : Editable?) = editable.let { it.toString() }

//FORMAT

fun iso8101(date : String) : LocalDateTime =
    LocalDateTime.from(DateTimeFormatter.ISO_DATE_TIME.parse(date))

fun z69_200(date : String) : String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return iso8101(date).format(formatter)
}

//FILE

fun Uri.toFile(context : Context): File {
    val picked = File(context.cacheDir, APP_NAME)
    val parcelFileDescriptor = context.contentResolver.openFileDescriptor(this, "r", null)
    parcelFileDescriptor?.let {
        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val outputStream = FileOutputStream(picked)
        IOUtils.copy(inputStream, outputStream)
    }
    return picked
}

//VIEW

fun showSoftKeyboard(context : Context, view: View) {
    if (view.requestFocus()){
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}
fun hideSoftKeyboard(context : Context, view: View) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

fun toastIt(context : Context?, text : String) = Toast.makeText(context, text, LENGTH_SHORT).show()
