package `in`.surelocal.surelocalaccessories.ui.login

import `in`.surelocal.surelocalaccessories.LoginActivity
import `in`.surelocal.surelocalaccessories.MainActivity
//import `in`.surelocal.surelocalaccessories.MainActivity
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.surelocal.surelocalaccessories.R
import `in`.surelocal.surelocalaccessories.application.SurelocalFragment
import android.content.Intent
import android.util.Patterns
import kotlinx.android.synthetic.main.log_in_fragment.*

class LoginFragment : SurelocalFragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.log_in_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

        initialise()
    }

    private fun initialise() {
        log_in_btn.setOnClickListener{
            val email=log_in_email_edt.text.toString()
            val password = log_in_password_edt.text.toString()
            if (email === "" || !Patterns.EMAIL_ADDRESS.matcher(email).matches() || password === "") {
                log_in_email_til.error = "Please enter a valid email."
                log_in_password_til.error = "Please enter a valid password."
            } else {
                login(email, password)
            }
        }

    }

    private fun login(email: String, password: String) {
        log_in_progress_bar.visibility = View.VISIBLE
        mAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(context as LoginActivity) {task->
                if (task.isSuccessful) {
                //    Log.d("LOGIN_FRAGMENT", "signInWithEmail:success")
                    log_in_progress_bar.visibility = View.GONE
                    gotoMainActivity()
                } else {
                  //  Log.d("LOGIN_FRAGMENT", "signInWithEmail:failure", task.exception)
                    log_in_email_til.error = "Please enter a valid email."
                    log_in_password_til.error = "Please enter a valid password."
                    showToast("Authentication failed.")
                    log_in_progress_bar.visibility = View.GONE
                }
            }


    }

     fun gotoMainActivity() {
        startActivity(Intent(context as LoginActivity, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        (context as LoginActivity).finish()
         showToast("Login Successfully...")
    }

}