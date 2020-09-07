package `in`.surelocal.surelocalaccessories.ui.splash

import `in`.surelocal.surelocalaccessories.*
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenFragment : Fragment() {

    var firebaseAuth = FirebaseAuth.getInstance()

    companion object {
        fun newInstance() = SplashScreenFragment()
    }

    private lateinit var viewModel: SplashScreenViewModel


    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            delay(2000)
            if (firebaseAuth.currentUser != null) {
                goMainActivity()
            } else {
                goLoginActivity()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_screen_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SplashScreenViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun goLoginActivity() {
        startActivity(
            Intent(context as SplashScreenActivity, LoginActivity::class.java).setFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP
            )
        )
        (context as SplashScreenActivity).finish()

    }

    private fun goMainActivity() {
        startActivity(
            Intent(
                context as SplashScreenActivity,
                MainActivity::class.java
            ).setFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP
            )
        )
        (context as SplashScreenActivity).finish()

    }
}