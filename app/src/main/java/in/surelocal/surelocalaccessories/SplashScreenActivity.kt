package `in`.surelocal.surelocalaccessories

import `in`.surelocal.surelocalaccessories.ui.login.LoginFragment.Companion.newInstance
import `in`.surelocal.surelocalaccessories.ui.splash.SplashScreenFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SplashScreenFragment.newInstance())
                .commitNow()
        }
    }
}