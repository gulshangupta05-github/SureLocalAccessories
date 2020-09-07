package `in`.surelocal.surelocalaccessories.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import `in`.surelocal.surelocalaccessories.R
import `in`.surelocal.surelocalaccessories.ShowDataActivity
import `in`.surelocal.surelocalaccessories.application.SurelocalFragment
import `in`.surelocal.surelocalaccessories.datalayer.EmployeeItemGet
import `in`.surelocal.surelocalaccessories.ui.accessories.AssessoriesFragment
import android.content.Intent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_employee.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : SurelocalFragment() {


    private lateinit var firestore: FirebaseFirestore
    private lateinit var homeViewModel: HomeViewModel
    var mUserName = ArrayList<String>()
    var employee_person_name = ""

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
    }

    private fun initialiseEmployeeName() {

        firestore.collection("accessories").get().addOnSuccessListener {
            val users =it.toObjects(EmployeeItemGet::class.java)
            mUserName.add("select Employee name")
            for(user in users){
                mUserName.add(user.user_name)
                val spnAdapter=ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,mUserName)
                spinner_ADEF.adapter=spnAdapter
            }
        }
        spinner_ADEF.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0-> {
//                        progress_bar.visibility=View.GONE
                    }
                    else ->{
//                        progress_bar.visibility=View.VISIBLE
                        employee_person_name=mUserName[position]
                        startActivity(Intent(requireContext(),ShowDataActivity::class.java))
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }
}