package `in`.surelocal.surelocalaccessories.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import `in`.surelocal.surelocalaccessories.R
import `in`.surelocal.surelocalaccessories.ShowDataAccessoriesActivity
import `in`.surelocal.surelocalaccessories.ShowDataEmployeeActivity
import `in`.surelocal.surelocalaccessories.application.SurelocalFragment
import `in`.surelocal.surelocalaccessories.datalayer.EmployeeItemGet
import `in`.surelocal.surelocalaccessories.datalayer.ProductAccessoriesGet
import android.content.Intent
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.*


private const val TAG = "HomeFragment"

class HomeFragment : SurelocalFragment() {


    private lateinit var firestore: FirebaseFirestore
    private lateinit var homeViewModel: HomeViewModel
    var mUserName = ArrayList<String>()
    var mEmpUserId = ArrayList<String>()
    var mAccessoriesId= ArrayList<String>()
    var mAccessoriesName = ArrayList<String>()
    var employee_person_name = ""
    var accessories_name = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firestore = FirebaseFirestore.getInstance()
        initialiseEmployeeName()
        initialiseProductAccessories()
    }

    private fun initialiseEmployeeName() {
        firestore.collection("Employee_Accessories").get().addOnSuccessListener {
            val users = it.toObjects(EmployeeItemGet::class.java)
            mUserName.add("select Employee name")
//            for (user in users) {
//                mUserName.add(user.user_name)
//                mUserId.add()
//            }
//            mUserName.add(temp!!.user_name)
            mEmpUserId.add("")
            for(i in 0 until it.documents.size) {
                val temp = it.documents[i].toObject(EmployeeItemGet::class.java)
                mUserName.add(temp!!.user_name)
                mEmpUserId.add(it.documents[i].id)
            }
            val spnAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, mUserName)
            spinner_ADEF.adapter = spnAdapter
        }
        spinner_ADEF.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                    }
                    else -> {
                        employee_person_name = mEmpUserId[position]
                        startActivity(Intent(requireContext(),ShowDataEmployeeActivity::class.java).putExtra("doc_id",employee_person_name))
                        Log.d(TAG, "onItemSelected: intent show all data$employee_person_name")
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun initialiseProductAccessories() {
        firestore.collection("product_accessories").get().addOnSuccessListener {
                val products = it.toObjects(ProductAccessoriesGet::class.java)
                mAccessoriesName.add("Select Accessories Name")
//                for (product in products) {
//                    mAccessoriesName.add(product.name)
                    mAccessoriesId.add("")
                    for (i in 0 until it.documents.size) {
                        val temp = it.documents[i].toObject(ProductAccessoriesGet::class.java)
                        mAccessoriesName.add(temp!!.name)
                        mAccessoriesId.add(it.documents[i].id)
                    }
//                }
                val spnAdapterAcc = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, mAccessoriesName)
                spinner_product_ADEF.adapter = spnAdapterAcc
            }
        spinner_product_ADEF.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                    }
                    else -> {
                        accessories_name = mAccessoriesId[position]
                        startActivity(Intent(requireContext(), ShowDataAccessoriesActivity::class.java).putExtra("Acc_doc_Id",accessories_name))
                        Log.d(TAG, "onItemSelected: $accessories_name")
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}