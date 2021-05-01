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
    var name: String = "User"
    var complexity: Int = 3
    var isTimeEnabled: Boolean = false
    var questionsCount: Int = 10
    var points: Int = 0
    var currentQuestion: Int = -1
    val questions: ArrayList<Question> = arrayListOf()
    var timeIsRemaining = MutableLiveData<Int>()
    var percentageOfAAnswer =1
    var percentageOfBAnswer =1
    var percentageOfCAnswer =1
    var percentageOfDAnswer =1

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
