package id.davidpratama.advweek4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.davidpratama.advweek4.model.Student

class ListViewModel : ViewModel() {

    fun refresh(){
        val student1 = Student("104710487","Zahara","2004/01/19","4855556814","http://dummyimage.com/100x75.png/cc0000/ffffff")
        val student2 = Student("798523882","John","2012/08/13","3115111200","http://dummyimage.com/100x75.png/ff4444/ffffff")
        val student3 = Student("418201142","Inga","1996/06/01","7511245662","http://dummyimage.com/100x75.png/cc0000/ffffff")

        val students: ArrayList<Student> = arrayListOf(student1,student2,student3)

        studentID.value = students
        studentLoadError.value = false
        studentLiveDone.value = true
    }

    val studentID = MutableLiveData<List<Student>>()
    val studentLoadError = MutableLiveData<Boolean>()
    val studentLiveDone = MutableLiveData<Boolean>()
}