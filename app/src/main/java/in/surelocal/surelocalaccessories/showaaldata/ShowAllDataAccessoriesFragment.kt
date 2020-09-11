package `in`.surelocal.surelocalaccessories.showaaldata

import `in`.surelocal.surelocalaccessories.R
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.show_all_data_fragment.*


private const val TAG = "ShowAllDataFragment"
class ShowAllDataAccessoriesFragment(val doc_Id: String) : Fragment() {
//    companion object {
//        fun newInstance() = ShowAllDataFragment(doc_id!!)
//    }
    private lateinit var viewModel: ShowAllDataViewModel
        private lateinit var firestore: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.show_all_data_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ShowAllDataViewModel::class.java)


        firestore = FirebaseFirestore.getInstance()

        firestore.collection("product_accessories").document(doc_Id).get()
            .addOnSuccessListener {
                tv_name.text = it.data!!["name"].toString()
                tv_mobile.text = it.data!!["mobile"].toString()
                tv_other1.text = it.data!!["other1"].toString()
                tv_other2.text = it.data!!["other2"].toString()
                tv_other3.text = it.data!!["other3"].toString()
            }
            .addOnFailureListener {
                Log.d(TAG, "onCreate: ")
            }
    }

}