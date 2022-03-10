package id.davidpratama.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.davidpratama.advweek4.model.Student

class ListViewModel (application: Application) :AndroidViewModel(application) {

    fun refresh(){
       studentLoadError.value = false
        studentLiveDone.value = true

        queue = Volley.newRequestQueue( getApplication() )
        val url = "http://adv.jitusolution.com/student.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                response ->
                val sType = object : TypeToken<List<Student>>() { }.type
                val result = Gson().fromJson<List<Student>>(response, sType)
                studentID.value = result
                studentLiveDone.value = false

                Log.d("showvoley", result.toString())

                studentLiveDone.value = false
                Log.d("showvoley", response.toString())
            },
            {
                Log.d("showvoley", it.toString())
                studentLoadError.value = false
                studentLiveDone.value = false
            })

            stringRequest.tag = TAG
             queue?.add(stringRequest)

    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    val TAG = "volleyTag"
    private var queue: RequestQueue? = null
    val studentID = MutableLiveData<List<Student>>()
    val studentLoadError = MutableLiveData<Boolean>()
    val studentLiveDone = MutableLiveData<Boolean>()
}