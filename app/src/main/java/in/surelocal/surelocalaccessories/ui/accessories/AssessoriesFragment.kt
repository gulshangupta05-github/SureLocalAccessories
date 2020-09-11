package `in`.surelocal.surelocalaccessories.ui.accessories

import `in`.surelocal.surelocalaccessories.MainActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import `in`.surelocal.surelocalaccessories.R
import `in`.surelocal.surelocalaccessories.datalayer.ProductAccessories
import `in`.surelocal.surelocalaccessories.ui.gallery.AssessoriesViewModel
import android.content.Intent
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_assessories.*
import kotlinx.android.synthetic.main.fragment_assessories.etMobile
import kotlinx.android.synthetic.main.fragment_assessories.etName
import kotlinx.android.synthetic.main.fragment_employee.*


private const val TAG = "AssessoriesFragment"

class AssessoriesFragment : Fragment() {

    private lateinit var galleryViewModel: AssessoriesViewModel
    private lateinit var firebase: FirebaseFirestore
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProviders.of(this).get(AssessoriesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_assessories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebase = FirebaseFirestore.getInstance()
        btnCreateNewAccessories.setOnClickListener {
            val aName = etName.text.toString().trim()
            val aMobile = etMobile.text.toString().trim()
            val aOthers1 = et_others1.text.toString().trim()
            val aOthers2 = et_others2.text.toString().trim()
            val aOthers3 = et_others3.text.toString().trim()
            val doc_getid = aName + aMobile
            if (isTrue(aName, aMobile, aOthers1, aOthers2, aOthers3)) {
                tv_product_Error.text = ""
                firebase.collection("product_accessories").document(doc_getid).set(
                    ProductAccessories(
                        Acc_doc_Id = doc_getid,
                        name = aName,
                        mobile = aMobile,
                        other1 = aOthers1,
                        other2 = aOthers2,
                        other3 = aOthers3
                    )
                )
                    .addOnSuccessListener {
                        btnCreateNewAccessories.text = "Uploading data..."
                        Toast.makeText(requireContext(), "Accessories Created", Toast.LENGTH_LONG)
                            .show()
                        startActivity(Intent(requireContext(), MainActivity::class.java))
                        Log.d(TAG, "onActivityCreated: success $it")
                    }.addOnFailureListener {
                        Log.d(TAG, "onViewCreated: ${it.message}")
                        Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_LONG).show()
                    }
            }
//            tvClear.setOnClickListener {
//                makeClearAllFields()
//
//            }
        }
    }


//    private fun makeClearAllFields() {
//        etName.text.clear()
//        etMobile.text.clear()
//        etEmail.text.clear()
//        etEmproll.text.clear()
//        et_monitor_model.text.clear()
//        et_keyboard_mouse.text.clear()
//        etwires.text.clear()
//
//    }

    private fun isTrue(
        sName: String,
        sMobile: String,
        sEmail: String,
        sEmpRoll: String,
        sMonitorModel: String
    ): Boolean {

        if (sName.isEmpty()) {
            til_Name.error = "Field Required"
            etEmail.requestFocus()
            return false
        } else if (sMobile.isEmpty() || sMobile.length != 10) {
            til_Mobile.error = "Empty or Invalid"
            etMobile.requestFocus()
            tilName.error = null
            return false
        } else if (sEmail.isEmpty()) {
            til_other1.error = "Empty or Invalid"
            et_others1.requestFocus()
            til_other1.error = null
            return false
        } else if (sEmpRoll.isEmpty()) {
            tilothers2.error = "Field Required"
            et_others2.requestFocus()
            return false
        } else if (sMonitorModel.isEmpty()) {
            til_other3.error = "Field Required"
            et_others3.requestFocus()
            return false
        }
        return true
    }
}