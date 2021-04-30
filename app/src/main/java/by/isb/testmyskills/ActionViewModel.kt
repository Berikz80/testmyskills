package by.isb.testmyskills

import android.R
import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


class ActionViewModel : ViewModel() {

    var name: String = "User"
    var complexity: Int = 3
    var isTimeEnabled: Boolean = false
    var questionsCount: Int = 10
    var points: Int = 0
    var currentQuestion: Int = -1
    val questions: ArrayList<Question> = arrayListOf()
    val timeIsLeft = MutableLiveData<Int>()

    fun startTimer(){
        object : CountDownTimer(60000,1000){
            override fun onTick(millisUntilFinished: Long) {
                    timeIsLeft.value =(millisUntilFinished/1000).toInt()
            }

            override fun onFinish() {
                cancel()
            }
        }.start()
    }


}