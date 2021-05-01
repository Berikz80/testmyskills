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

    internal lateinit var timer: CountDownTimer
    var name: String = "User"
    var complexity: Int = 3
    var maxTime: Int = 0
    var questionsCount: Int = 10
    var points: Int = 0
    var currentQuestion: Int = -1
    val questions: ArrayList<Question> = arrayListOf()
    var timeIsLeft = MutableLiveData<Int>()
    lateinit var countDownTimer: CountDownTimer

    fun startTimer() {
        countDownTimer = object : CountDownTimer((maxTime * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeIsLeft.value = (millisUntilFinished / 1000).toInt()
            }

            override fun onFinish() {
                cancel()
            }
        }.start()
    }

    fun stopTimer() {
        if (currentQuestion > 0) countDownTimer.cancel()
    }
}
