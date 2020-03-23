package sero.com.microcosmos.utils

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.google.android.material.textfield.TextInputEditText

fun getValue(editable : TextInputEditText) = editable.text.toString()

fun toastIt(context : Context?, text : String) = Toast.makeText(context, text, LENGTH_SHORT).show()