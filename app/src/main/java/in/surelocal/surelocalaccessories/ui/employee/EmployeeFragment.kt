package `in`.surelocal.surelocalaccessories.ui.employee

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
import `in`.surelocal.surelocalaccessories.datalayer.EmployeeItemSet
import android.content.Intent
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_employee.*
import java.util.regex.Pattern


private const val TAG = "EmployeeFragment"
class EmployeeFragment : Fragment() {

    private lateinit var firebase: FirebaseFirestore
    private lateinit var slideshowViewModel: EmployeeViewModel

    //    val userRef = firebase.collection("accessories")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProviders.of(this).get(EmployeeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_employee, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        firebase = FirebaseFirestore.getInstance()
        btnCreateEmployee.setOnClickListener {
            val sName = etName.text.toString().trim()
            val sMobile = etMobile.text.toString().trim()
            val sEmail = etEmail.text.toString().trim()
            val sEmpRoll = etEmproll.text.toString().trim()
            val sMonitor_model = et_monitor_model.text.toString().trim()
            val sKeyboard_mouse = et_keyboard_mouse.text.toString().trim()
            val sWires = etwires.text.toString().trim()

            if (isTrue(sName, sMobile, sEmail, sEmpRoll, sMonitor_model, sKeyboard_mouse, sWires)) {
                tvError.text = ""
                firebase.collection("accessories").document().set(
                    EmployeeItemSet(
                        user_name = sName,
                        user_mobile = sMobile,
                        user_email = sEmail,
                        user_EmpRoll = sEmpRoll,
                        user_monitor_modelno = sMonitor_model,
                        user_keyboard_mouse = sKeyboard_mouse,
                        user_allwires = sWires
                    )
                ).addOnSuccessListener {
                    btnCreateEmployee.text = "Uploading data..."
                    Toast.makeText(requireContext(), "user Created", Toast.LENGTH_LONG).show()
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    Log.d(TAG, "onActivityCreated: success $it")
                }
                    .addOnFailureListener {
                        Toast.makeText(requireContext(), "user creation failed", Toast.LENGTH_LONG)
                            .show()
                        Log.d(TAG, "onActivityCreated: failed ${it.message}")
                    }
            }
        }
        tvClear.setOnClickListener {
            makeClearAllFields()

        }
    }


    private fun makeClearAllFields() {
        etName.text.clear()
        etMobile.text.clear()
        etEmail.text.clear()
        etEmproll.text.clear()
        et_monitor_model.text.clear()
        et_keyboard_mouse.text.clear()
        etwires.text.clear()
        Log.d(TAG, "makeClearAllFields: ${makeClearAllFields()}")
    }

    private fun isTrue(
        sName: String,
        sMobile: String,
        sEmail: String,
        sEmpRoll: String,
        sMonitorModel: String,
        sKeyboardMouse: String,
        sWires: String
    ): Boolean {

        if (sName.isEmpty()) {
            tilName.error = "Field Required"
            etName.requestFocus()
            return false
        } else if (sMobile.isEmpty() || sMobile.length != 10) {
            tilMobile.error = "Empty or Invalid"
            etMobile.requestFocus()
            tilName.error = null
            return false
        } else if (sEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(sEmail).matches()) {
            tilEmail.error = "Empty or Invalid"
            tilEmail.requestFocus()
            tilMobile.error = null
            return false
        } else if (sEmpRoll.isEmpty()) {
            tilEmproll.error = "Field Required"
            etEmproll.requestFocus()
            return false
        } else if (sMonitorModel.isEmpty()) {
            tilMonitermodel.error = "Field Required"
            etMobile.requestFocus()
            return false
        } else if (sKeyboardMouse.isEmpty()) {
            tilkayboard_mouse.error = "Field Required"
            et_keyboard_mouse.requestFocus()
            return false
        } else if (sWires.isEmpty()) {
            tilAllwires.error = "Field Required"
            etwires.requestFocus()
            return false
        }
        return true

    }
}