package by.isb.testmyskills

import android.R
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


class ActionViewModel : ViewModel() {

    var name: String = ""
    var complexity: Int = 3
    var isTimeEnabled: Boolean = false
    var questionsCount: Int = 10
    var points: Int = 0
    var currentQuestion: Int = -1
    val questions: ArrayList<Question> = arrayListOf()


}