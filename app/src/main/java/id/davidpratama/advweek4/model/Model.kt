package id.davidpratama.advweek4.model

import com.google.gson.annotations.SerializedName

data class Student(
    val id: String?,
    @SerializedName("student_name")
    val name: String?,
    @SerializedName("birth_of_date")
    val bod: String?,
    val phone: String?,
    @SerializedName("phone_url")
    val photoUrl: String?
)