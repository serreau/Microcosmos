package sero.com.microcosmos.utils

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit : Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun getValue(editable : TextInputEditText) = editable.text.toString()

fun toastIt(context : Context?, text : String) = Toast.makeText(context, text, LENGTH_SHORT).show()