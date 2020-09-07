package `in`.surelocal.surelocalaccessories.showdata

import `in`.surelocal.surelocalaccessories.R
import `in`.surelocal.surelocalaccessories.datalayer.EmpdetailsGet
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.show_data_fragment.*

private const val TAG = "ShowDataFragment"
class ShowDataFragment : Fragment() {

    lateinit var firestore: FirebaseFirestore
    private var mDatas = ArrayList<EmpdetailsGet>()

    companion object {
        fun newInstance() = ShowDataFragment()
    }

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
        val empName = et_name.text.toString().trim()
        val empMobile = et_mobile.text.toString().trim()
        val empEmail = et_email.text.toString().trim()
        val empRoll = et_emp_roll.toString().trim()
        val empMonitor = et_monitor_model_no.toString().trim()
        val empKeyboard = et_key_mouse.toString().trim()
        val empAllwires = et_all_wires.toString().trim()
        firestore.collection("accessories").document("WNyitSXXD0HMZdScLbbX").get()
            .addOnSuccessListener {
//            it.forEach {
                val document_id=it.id
//                val users = it.toObject(EmpdetailsGet::class.java)
                mDatas.add(
                    EmpdetailsGet(
                        document_id = document_id,
                        user_name = empName,
                        user_mobile = empMobile,
                        user_email = empEmail,
                        user_monitor_modelno = empMonitor,
                        user_keyboard_mouse = empKeyboard,
                        user_EmpRoll = empRoll,
                        user_allwires = empAllwires
                    )
                )
//            }

        }.addOnFailureListener {
            Log.d(TAG, "onActivityCreated: ${it.message}")
        }
    }
}
