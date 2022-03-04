package id.davidpratama.advweek4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.davidpratama.advweek4.model.Student

class DetailViewModel : ViewModel(){
    val studentLD = MutableLiveData<Student>()

    fun fetch(){
        val student1 = Student("16055","Novie","1998/03/28","57184478",
        "http://dummyimage.com/100x75.png/cc0000/ffffff")

        studentLD.value = student1
    }
}