package sero.com.microcosmos.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import sero.com.microcosmos.R
import sero.com.microcosmos.view.utils.SPLASH_SCREEN_DELAY
import sero.com.microcosmos.view.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        Handler().postDelayed(SPLASH_SCREEN_DELAY){
            startActivity(Intent(baseContext, MainActivity::class.java))
            finish()
        }
    }
}