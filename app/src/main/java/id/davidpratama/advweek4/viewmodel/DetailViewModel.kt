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
import id.davidpratama.advweek4.model.Student

class DetailViewModel (application: Application) : AndroidViewModel(application){
    val studentLD = MutableLiveData<Student>()
    val TAG = "volleyTag"
    private var queue: RequestQueue ?= null

    fun fetch(id:String){
//        val student1 = Student("16055","Novie","1998/03/28","57184478",
//        "http://dummyimage.com/100x75.png/cc0000/ffffff")

//        studentLD.value = student1

        queue = Volley.newRequestQueue(getApplication())
        var url = "http://adv.jitusolution.com/student.php?=$id"

        val stringRequest = StringRequest(
            Request.Method.GET, url,{
                response ->
                val result = Gson().fromJson<Student>(response,Student::class.java)
                studentLD.value = result

                Log.d("showvolley",response.toString())
            },{
                Log.d("errorolley", it.toString())
            }
        ).apply {
            tag = "TAG"
        }

        queue?.add(stringRequest)

    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}