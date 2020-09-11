package `in`.surelocal.surelocalaccessories.ui.employee

import `in`.surelocal.surelocalaccessories.MainActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import `in`.surelocal.surelocalaccessories.R
import `in`.surelocal.surelocalaccessories.datalayer.EmployeeItemGet
import `in`.surelocal.surelocalaccessories.datalayer.EmployeeItemSet
import android.content.Intent
import android.util.Log
import android.util.Patterns
import android.widget.*
import com.google.common.util.concurrent.Monitor
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_employee.*
import kotlinx.android.synthetic.main.fragment_employee.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.regex.Pattern


private const val TAG = "EmployeeFragment"

class EmployeeFragment : Fragment() {


    lateinit var data_holds: String
    private lateinit var firebase: FirebaseFirestore
    private lateinit var slideshowViewModel: EmployeeViewModel
    val monitor = arrayOf("select monitor", "1", "2", "3", "4")
    val keyboard_mouse = arrayOf("select keyboard mouse", "1", "2", "3", "4", "5.")
    val hdmi = arrayOf("select hdmi", "1", "2", "3", "4", "5")
    val power_adapter = arrayOf("select power adapter", "1", "2", "3", "4", "5")
    val desktop_power_adapter = arrayOf("select desktop power cable", "1", "2", "3", "4", "5")


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

        val sName = etName.text.toString().trim()
        val sMobile = etMobile.text.toString().trim()
        val sEmail = etEmail.text.toString().trim()
        val sEmpRoll = etEmproll.text.toString().trim()
//            val sMonitor_model = et_monitor_model.text.toString().trim()
//            val sKeyboard_mouse = et_keyboard_mouse.text.toString().trim()
//            val sWires = etwires.text.toString().trim()
        val doc_id = "$sName$sMobile"
//        btnCreateEmployee.setOnClickListener {
//            data_holds = firebase.collection("Employee_Accessories").document(doc_id).set(
//                "doc_id" to doc_id,
//            "user_name" to sName,
//            "user_mobile" to sMobile,
//            "user_email" to sEmail,
//            "user_EmpRoll" to sEmpRoll
//              "Monitor" to monitor,
//                "keyboard" to keyboard_mouse,
//                "Hdmi" to hdmi,
//                "power_adapter" to power_adapter,
//                "monitor_Adapter" to desktop_power_adapter
//            )
//        xx = doc_id
//        if (isTrue(sName, sMobile, sEmail, sEmpRoll)) {
//            tvError.text = ""
//                val idss = "$sName$sMobile"
//                firebase.collection("Employee_Accessories").document().set(
//                    EmployeeItemSet(
//                        doc_id = doc_id,
//                        user_name = sName,
//                        user_mobile = sMobile,
//                        user_email = sEmail,
//                        user_EmpRoll = sEmpRoll
//                        user_monitor_modelno = sMonitor_model,
//                        user_keyboard_mouse = sKeyboard_mouse,
//                        user_allwires = sWires
//                    )
//                ).addOnSuccessListener {
//                    btnCreateEmployee.text = "Uploading data..."
//                    Toast.makeText(requireContext(), "user Created", Toast.LENGTH_LONG).show()
//                    startActivity(Intent(requireContext(), MainActivity::class.java))
//                    Log.d(TAG, "onActivityCreated: success $it")
//                }
//                    .addOnFailureListener {
//                        Toast.makeText(requireContext(), "user creation failed", Toast.LENGTH_LONG)
//                            .show()
//                        Log.d(TAG, "onActivityCreated: failed ${it.message}")
//                    }
//        }
//        }
        tvClear.setOnClickListener {
//            makeClearAllFields()
        }
        allSpinner()
    }


    private fun allSpinner() {

        spinner_Monitor.adapter =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, monitor)
        spinner_Monitor.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(

                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> {
                        }
                        else -> {

                            data_holds = monitor[position]
//                            val moni_tor=monitor[position]
//                            firebase.collection("Employee_Accessories").document(xx).set("Monitor" to xx)
                            Toast.makeText(requireContext(), "$data_holds", Toast.LENGTH_LONG).show()
                            Log.d(TAG, "MONITOR LOG : ${data_holds}")
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        spinner_keyboard_mouse.adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            keyboard_mouse
        )
        spinner_keyboard_mouse.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    when (position) {
                        0 -> {
                        }
                        else -> {
                            data_holds = keyboard_mouse[position]
                            val keyboard_mouse = keyboard_mouse[position]
//                            firebase.collection("Employee_Accessories").document(xx).set("keyboard_mouse" to keyboard_mouse)
                            Toast.makeText(requireContext(), "$data_holds", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

        spinner_hdmi.adapter =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, hdmi)
        spinner_hdmi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                when (position) {
                    0 -> {
                    }
                    else -> {
                        data_holds = hdmi[position]
//                        val hdmi = hdmi[position]
//                        firebase.collection("Employee_Accessories").document(xx).set("Hdmi" to xx)
                        Toast.makeText(requireContext(), "$data_holds", Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        spinner_power_adapter.adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            power_adapter
        )
        spinner_power_adapter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                when (position) {
                    0 -> {
                    }
                    else -> {
                        data_holds = power_adapter[position]
//                        val power_adapter = power_adapter[position]
//                        firebase.collection("Employee_Accessories").document(xx).set("power_adapter" to xx)
                        Toast.makeText(requireContext(), "$data_holds", Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinner_monitor_adapter.adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            desktop_power_adapter
        )
        spinner_monitor_adapter.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    when (position) {
                        0 -> {
                        }
                        else -> {
                            data_holds = monitor[position]
                            val monitor_adapter = desktop_power_adapter[position]
                            Toast.makeText(requireContext(), monitor_adapter, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    private fun makeClearAllFields() {
        etName.text.clear()
        etMobile.text.clear()
        etEmail.text.clear()
        etEmproll.text.clear()
//        et_monitor_model.text.clear()
//        et_keyboard_mouse.text.clear()
//        etwires.text.clear()
        Log.d(TAG, "makeClearAllFields: ${makeClearAllFields()}")
    }

    private fun isTrue(
        sName: String,
        sMobile: String,
        sEmail: String,
        sEmpRoll: String
//        sMonitorModel: String,
//        sKeyboardMouse: String,
//        sWires: String
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
        }
//        else if (sMonitorModel.isEmpty()) {
//            tilMonitermodel.error = "Field Required"
//            etMobile.requestFocus()
//            return false
//        } else if (sKeyboardMouse.isEmpty()) {
//            tilkayboard_mouse.error = "Field Required"
//            et_keyboard_mouse.requestFocus()
//            return false
//        } else if (sWires.isEmpty()) {
//            tilAllwires.error = "Field Required"
//            etwires.requestFocus()
//            return false
//        }
        return true

    }
}
//mUserId.add("")
//for(i in 0 until it.documents.size) {
//    var temp = it.documents[i].toObject(EmployeeItemGet::class.java)
//    mUserName.add(temp!!.user_name)
//    mUserId.add(it.documents[i].id)
//}