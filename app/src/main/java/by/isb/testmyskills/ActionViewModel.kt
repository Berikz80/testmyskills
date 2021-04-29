package by.isb.testmyskills

import android.R
import androidx.lifecycle.ViewModel
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


class ActionViewModel : ViewModel() {

    val name: String,
    val complexity: Int,
    val isTimeEnabled: Boolean,
    val questionsCount: Int,
    var points: Int = 0
    lateinit var questions: ArrayList<Question>


}