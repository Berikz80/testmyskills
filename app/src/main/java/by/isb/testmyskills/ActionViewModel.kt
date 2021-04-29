package by.isb.testmyskills

import android.R
import androidx.lifecycle.ViewModel
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


class ActionViewModel : ViewModel() {

    val name: String = ""
    val complexity: Int = 1
    val isTimeEnabled: Boolean = false
    val questionsCount: Int = 10
    var points: Int = 0
    var currentQuestion : Int = 0
    lateinit var questions: ArrayList<Question>


}