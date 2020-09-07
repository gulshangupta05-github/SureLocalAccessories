package `in`.surelocal.surelocalaccessories.application

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

abstract class SurelocalFragment :Fragment() {


    lateinit var mAuth :FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                mAuth = FirebaseAuth.getInstance()
    }

    fun isLoggedIn() :Boolean{
        val currentUser =mAuth.currentUser
        return true
    }

    fun showToast(message:String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }
}