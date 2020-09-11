package `in`.surelocal.surelocalaccessories

import `in`.surelocal.surelocalaccessories.showaaldata.ShowAllDataAccessoriesFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ShowDataAccessoriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val acc_doc_id = intent.getStringExtra("Acc_doc_Id")
        setContentView(R.layout.activity_show_data_accessories)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ShowAllDataAccessoriesFragment(acc_doc_id!!))
                .commitNow()
        }
    }
}