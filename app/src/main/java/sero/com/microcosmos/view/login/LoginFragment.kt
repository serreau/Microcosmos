package sero.com.microcosmos.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_login.*
import sero.com.microcosmos.R
import sero.com.microcosmos.utils.getValue
import sero.com.microcosmos.utils.toastIt

class LoginFragment : Fragment() {

    private val viewmodel: LoginViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(viewmodel.stillConnected(context)) findNavController().navigate(R.id.profileFragment)
        initListener();
    }

    private fun initListener() {
        connectButton.setOnTouchListener { _, _ ->
            if (viewmodel.connect(context, getValue(login), getValue(password))){
                findNavController().navigate(R.id.searchFragment)
                toastIt(context, getString(R.string.activity_login__login_success))
            }
            else
                toastIt(context, getString(R.string.activity_login__connection_refused))
            true
        }
        signUpButton.setOnClickListener { _ -> findNavController().navigate(R.id.signUpFragment) }
    }
}