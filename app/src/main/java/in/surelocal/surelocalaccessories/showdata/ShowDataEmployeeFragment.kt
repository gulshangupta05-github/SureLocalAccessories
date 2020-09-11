package `in`.surelocal.surelocalaccessories.showdata

import `in`.surelocal.surelocalaccessories.R
import `in`.surelocal.surelocalaccessories.datalayer.EmpdetailsGet
import `in`.surelocal.surelocalaccessories.ui.employee.EmployeeFragment
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.show_data_fragment.*
import kotlinx.android.synthetic.main.show_data_fragment.tv_mobile
import kotlinx.android.synthetic.main.show_data_fragment.tv_name

private const val TAG = "ShowDataFragment"

class ShowDataEmployeeFragment(
    val doc_id: String
) : Fragment() {

    lateinit var firestore: FirebaseFirestore
    private var mDatas = ArrayList<EmpdetailsGet>()

//    companion object {
//        fun newInstance() = ShowDataFragment()
//    }

    private lateinit var viewModel: ShowDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.show_data_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ShowDataViewModel::class.java)

        firestore = FirebaseFirestore.getInstance()

        firestore.collection("Employee_Accessories").document(doc_id).get()
            .addOnSuccessListener {
//                val document_id=it.id

                Log.d(TAG, "onActivityCreated: ${it.data!!["user_name"]}")
                Log.d(TAG, "onActivityCreated: ${it.data!!["user_mobile"]}")
                tv_name.text = it.data!!["user_name"].toString()
                tv_mobile.text = it.data!!["user_mobile"].toString()
                tv_email.text = it.data!!["user_email"].toString()
                tv_emp_role.text = it.data!!["user_EmpRoll"].toString()
                tv_monitor_model.text = it.data!!["user_monitor_modelno"].toString()
                tv_key_mouse.text = it.data!!["user_keyboard_mouse"].toString()
                tv_all_wires.text = it.data!!["user_allwires"].toString()
//                tv_doc_id_get.text = it.data!!["doc_id"].toString()
            }.addOnFailureListener {
                Log.d(TAG, "onActivityCreated: ${it.message}")
            }

        et_edit_employee.setOnClickListener {
//            startActivity(Intent(requireContext(),EmployeeFragment::class.java))
            val intent = Intent(requireContext(),EmployeeFragment::class.java)
            startActivity(intent)
        }

    }
}
