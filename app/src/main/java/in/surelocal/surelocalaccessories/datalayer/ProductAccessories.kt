package `in`.surelocal.surelocalaccessories.datalayer

import com.google.gson.annotations.SerializedName

class ProductAccessories(

    @SerializedName("Acc_doc_Id")
    val Acc_doc_Id:String,

    @SerializedName("name")
    val name: String,

    @SerializedName("mobile")
    val mobile: String,

    @SerializedName("other1")
    val other1: String,

    @SerializedName("other2")
    val other2: String,

    @SerializedName("other3")
    val other3: String,
) {
}