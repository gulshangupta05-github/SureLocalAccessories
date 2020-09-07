package `in`.surelocal.surelocalaccessories.datalayer

import com.google.gson.annotations.SerializedName

class EmployeeItemSet (

    @SerializedName("user_name")
    val user_name:String,

    @SerializedName("user_mobile")
    val user_mobile:String,

    @SerializedName ("user_email")
    val user_email:String,

    @SerializedName("user_EmpRoll")
    val user_EmpRoll:String,

    @SerializedName("user_monitor_modelno")
    val user_monitor_modelno:String,

    @SerializedName("user_keyboard_mouse")
    val user_keyboard_mouse:String,

    @SerializedName("user_allwires")
    val user_allwires:String
    ) {
}