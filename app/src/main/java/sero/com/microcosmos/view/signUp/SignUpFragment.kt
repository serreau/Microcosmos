package sero.com.microcosmos.view.signUp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.esafirm.imagepicker.features.ImagePicker
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment__sign_up.*
import kotlinx.android.synthetic.main.fragment_create_job.createJobButton
import sero.com.microcosmos.R
import sero.com.microcosmos.utils.getValue
import sero.com.microcosmos.utils.toastIt
import java.io.File


class SignUpFragment : Fragment() {
    private val model: SignUpViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment__sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
    }

    private fun initListener() {
        createJobButton.setOnClickListener {
            val success = model.insertUser(getValue(firstname),
                getValue(lastname),
                getValue(phone),
                getValue(mail),
                getValue(password))
            if(success) {
                toastIt(context, getString(R.string.activity_sign_up__sign_up_success))
                findNavController().navigate(R.id.loginFragment)
            }
            else
                toastIt(context, getString(R.string.activity_sign_up__sign_up_error))
        }
        image.setOnClickListener{
            ImagePicker.create(this)
                .single()
                .start();
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            val picked = Uri.fromFile( File(ImagePicker.getFirstImageOrNull(data).path))
            Picasso.get()
                .load(picked)
                .placeholder(R.mipmap.bee)
                .resize(image.width, image.height)
                .centerInside()
                .error(R.mipmap.bee)
                .into(image)
        }
    }
}