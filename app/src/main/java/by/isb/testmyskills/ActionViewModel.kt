package by.isb.testmyskills

import android.R
import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


class ActionViewModel : ViewModel() {

   internal  lateinit var timer : CountDownTimer
    val name: String = ""
    val complexity: Int = 1
    var isTimeEnabled: Boolean = false
    val questionsCount: Int = 10
    var points : Int =0
    var currentQuestion : Int = -1
    val questions : ArrayList<Question> = arrayListOf()
    var timeIsRemaining = MutableLiveData<Int>()

fun startTimer () {
   timer =  object : CountDownTimer(60000, 1000){

       override fun onFinish() {
           timer.cancel()
       }
        override fun onTick(p0 : Long) {
           val timeIsLeft = p0/1000
            timeIsRemaining.value = timeIsLeft.toInt()
        }
    }.start()
}

}
