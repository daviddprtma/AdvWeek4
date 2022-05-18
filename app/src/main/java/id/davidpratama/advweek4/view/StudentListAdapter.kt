package id.davidpratama.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.davidpratama.advweek4.R
import id.davidpratama.advweek4.databinding.StudentListItemBinding
import id.davidpratama.advweek4.model.Student
import id.davidpratama.advweek4.util.loadImage
import kotlinx.android.synthetic.main.fragment_student_detail.view.*
import kotlinx.android.synthetic.main.student_list_item.view.*

//Array List Student didapatkan dari Model.kt yang bernama Student
class StudentListAdapter(val studentList: ArrayList<Student>)
    : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(),ButtonDetailClickListener{
    class StudentViewHolder(var view: StudentListItemBinding) : RecyclerView.ViewHolder(view.root)

    fun updateStudentList(newStudentList: List<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = DataBindingUtil.inflate<StudentListItemBinding>(inflater,R.layout.student_list_item,parent,false)
//        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.student = studentList[position]
        holder.view.listener = this
//        val student = studentList[position]
//
//        with(holder.view){
//            txtID.text = student.id
//            txtName.text = student.name
//
//            btnDetail.setOnClickListener {
//                val action = student.id?.let { id->
//                    StudentListFragmentDirections.actionStudentDetail(id)
//                }
//                if(action !=null){
//                    Navigation.findNavController(it).navigate(action)
//                }
//            }
//            imageView.loadImage(student.photoUrl, progressBar)
//        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }
}