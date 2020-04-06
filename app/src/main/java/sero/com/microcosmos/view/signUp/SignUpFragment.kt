package sero.com.microcosmos.view.signUp

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment__sign_up.*
import sero.com.microcosmos.R
import sero.com.microcosmos.utils.getValue
import sero.com.microcosmos.utils.toFile
import sero.com.microcosmos.utils.toastIt
import java.io.File


class SignUpFragment : Fragment() {
    private val viewmodel: SignUpViewModel by viewModels()

    private val RESULT_LOAD_IMAGE: Int = 1
    var picked : File = File("")
    var uri : Uri = Uri.EMPTY

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment__sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {

        validate.setOnClickListener {
            picked = uri.toFile(context!!)
            val success = viewmodel.insertUser(getValue(firstname),
                getValue(lastname),
                getValue(phone),
                getValue(mail),
                getValue(password),
                picked
            )
            if(success) {
                toastIt(context, getString(R.string.activity_sign_up__sign_up_success))
                findNavController().navigate(R.id.loginFragment)
            }
            else
                toastIt(context, getString(R.string.activity_sign_up__sign_up_error))
        }
        image.setOnClickListener{
            val i = Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(i, RESULT_LOAD_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == RESULT_LOAD_IMAGE){
            uri = data?.data ?: Uri.EMPTY
            Glide.with(this)
                .load(uri)
                .placeholder(R.mipmap.bee)
                .centerInside()
                .error(R.mipmap.bee)
                .into(image)
        }
    }
}