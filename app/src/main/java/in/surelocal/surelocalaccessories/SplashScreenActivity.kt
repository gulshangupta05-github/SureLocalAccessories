package `in`.surelocal.surelocalaccessories

//import `in`.surelocal.surelocalaccessories.showdata.ShowDataFragment
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
//
//package `in`.surelocal.surelocalaccessories
//
//import `in`.surelocal.surelocalaccessories.showaaldata.ShowAllDataFragment
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.google.firebase.firestore.FirebaseFirestore
//
//
//private const val TAG = "ShowAllAccessories"
//
//class ShowAllAccessoriesActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val acc_doc_id=intent.getStringExtra("Acc_doc_Id")
//        setContentView(R.layout.activity_show_all_accessories)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, ShowAllDataFragment(acc_doc_id!!))
//                .commitNow()
//        }
//    }
//}












//
//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    val doc_id=intent.getStringExtra("doc_id")
//    setContentView(R.layout.activity_show_data)
//    if (savedInstanceState == null) {
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.container, ShowDataFragment(doc_id!!))
//            .commitNow()
//    }
//}
//}