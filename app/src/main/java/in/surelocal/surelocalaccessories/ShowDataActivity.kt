package `in`.surelocal.surelocalaccessories

import `in`.surelocal.surelocalaccessories.showdata.ShowDataFragment
import `in`.surelocal.surelocalaccessories.ui.splash.SplashScreenFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ShowDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ShowDataFragment.newInstance())
                .commitNow()
        }
    }
}