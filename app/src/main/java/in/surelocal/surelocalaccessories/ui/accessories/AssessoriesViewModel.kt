package `in`.surelocal.surelocalaccessories.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AssessoriesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Assessories Fragment"
    }
    val text: LiveData<String> = _text
}