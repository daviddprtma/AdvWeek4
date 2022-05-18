package id.davidpratama.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import id.davidpratama.advweek4.R
import id.davidpratama.advweek4.databinding.FragmentStudentDetailBinding
import id.davidpratama.advweek4.util.loadImage
import id.davidpratama.advweek4.viewmodel.DetailViewModel
import id.davidpratama.advweek4.viewmodel.ListViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(),ButtonUpdateStudentClickListener,ButtonCreateNotificationClickListener {
    private lateinit var  viewModel: DetailViewModel
    private lateinit var dataBinding:FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentStudentDetailBinding.inflate(inflater,container,false)
//        return inflater.inflate(R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var id = ""

        arguments?.let {
            id =StudentDetailFragmentArgs.fromBundle(requireArguments()).id
        }

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(id)

        observeViewModelDetailStudent()

        dataBinding.listenerupdate = this
        dataBinding.listenercreatenotification = this
    }

    private fun observeViewModelDetailStudent() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            txtId.setText(it.id)
            txtStudentName.setText(it.name)
            txtBod.setText(it.bod)
            txtPhone.setText(it.phone)
            photoUser.loadImage(it.photoUrl, progressBarDetailPhoto)

            var student = it
            btnNotif.setOnClickListener {
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotification(student.name.toString(),
                            "A new notification created",
                            R.drawable.ic_baseline_person_24)
                    }
            }
        })
    }

    override fun ButtonUpdateStudentClick(v: View) {
        Toast.makeText(v.context,"Student update",Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }

    override fun onButtonCreateNotificationClick(v: View) {
        Observable.timer(5,TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                Log.d("hellonotif","Notification delayed in 5 secs")
                MainActivity.showNotification(v.tag.toString(),"Notifications created :D",R.drawable.ic_baseline_person_24)
            }
    }
}