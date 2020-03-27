package sero.com.microcosmos.view.signUp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import sero.com.microcosmos.data.repository.RemoteUserRepository

class SignUpViewModel : ViewModel() {
    private val remoteUserRepository : RemoteUserRepository = RemoteUserRepository()

    fun insertUser(firstname : String,
                   lastname : String,
                   phone : String,
                   mail : String,
                   password : String) = runBlocking {
        remoteUserRepository.insert(firstname, lastname, phone, mail, password)
    }
}