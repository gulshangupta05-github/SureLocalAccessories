package `in`.surelocal.surelocalaccessories

import `in`.surelocal.surelocalaccessories.showdata.ShowDataEmployeeFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ShowDataEmployeeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val doc_id=intent.getStringExtra("doc_id")
        setContentView(R.layout.activity_show_data_employee)
        if (savedInstanceState == null) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ShowDataEmployeeFragment(doc_id!!))
            .commitNow()
    }
    }
}