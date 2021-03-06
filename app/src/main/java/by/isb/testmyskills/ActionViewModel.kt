package by.isb.testmyskills

import android.os.Build
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalTime
import java.util.*
import kotlin.collections.ArrayList

class ActionViewModel : ViewModel() {

    internal lateinit var timer: CountDownTimer
    var name: String = "User"
    var complexity: Int = 3
    var maxTime: Int = 0
    var questionsCount: Int = 10
    var usedHints = 0
    var points: Int = 0
    var timeFromStart:Int = 0
    var currentQuestion: Int = -1
    val questions: ArrayList<Question> = arrayListOf()
    var timeIsLeft = MutableLiveData<Int>()
    lateinit var countDownTimer: CountDownTimer
    var starTimer: Int = 0

    @RequiresApi(Build.VERSION_CODES.O)
    fun startTimer() {
        starTimer  = LocalTime.now().second
        countDownTimer = object : CountDownTimer((maxTime * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {

                timeIsLeft.value = (millisUntilFinished / 1000).toInt()
            }
            override fun onFinish() {


                    cancel()
            }
        }.start()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun stopTimer() {
        val currentTime = LocalTime.now().second
        timeFromStart = timeFromStart.plus(currentTime - starTimer)
        if (currentQuestion > 0) countDownTimer.cancel()

    }
}