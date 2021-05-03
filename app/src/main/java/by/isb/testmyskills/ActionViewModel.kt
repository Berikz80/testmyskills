package by.isb.testmyskills

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActionViewModel : ViewModel() {

    internal lateinit var timer: CountDownTimer
    var name: String = "User"
    var complexity: Int = 3
    var maxTime: Int = 0
    var questionsCount: Int = 10
    var points: Int = 0
    var currentQuestion: Int = -1
    val questions: ArrayList<Question> = arrayListOf()

    var timeIsRemaining = MutableLiveData<Int>()
    var percentageOfAAnswer =1
    var percentageOfBAnswer =1
    var percentageOfCAnswer =1
    var percentageOfDAnswer =1

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

