package by.isb.testmyskills

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

class ActionActivity : AppCompatActivity() {

    lateinit var viewModel: ActionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action)

        viewModel = ViewModelProvider(this).get(ActionViewModel::class.java)

        with(viewModel) {
            name = intent.getStringExtra("name") ?: "User"
            complexity = intent.getIntExtra("difficulty", 3)
            questionsCount = intent.getIntExtra("questions", 10)
            maxTime = intent.getIntExtra("timer", 0)
        }

        readQuestionsFromFile(viewModel.complexity)

        val time = findViewById<TextView>(R.id.timer)

        val answerButtons = arrayOf(
            findViewById<Button>(R.id.button_a),
            findViewById<Button>(R.id.button_b),
            findViewById<Button>(R.id.button_c),
            findViewById<Button>(R.id.button_d)
        )

        for (i in 0..3) {
            answerButtons[i].setOnClickListener {
                if (viewModel.questions[viewModel.currentQuestion].rightAnswer == i) {
                    viewModel.points += 100
                    answerButtons[i].setBackgroundColor(Color.GREEN)
                } else answerButtons[i].setBackgroundColor(Color.RED)

                android.os.Handler()
                    .postDelayed({
                        answerButtons[i].setBackgroundColor(Color.WHITE)
                        nextQuestion()
                    }, 400)
            }
        }

        viewModel.timeIsLeft.observe(this) {
            time.text = it?.toString() + getString(R.string.seconds)
            if (it < 11) time.setTextColor(Color.RED)
            else time.setTextColor(Color.GRAY)
            if (it == 0) {
                Toast.makeText(this, getString(R.string.time_is_out), Toast.LENGTH_LONG).show()
                nextQuestion()
            }
        }

        val friendHelp = findViewById<Button>(R.id.call_friend)
        var friendHelpUsed = true

        val fiftyFifty = findViewById<Button>(R.id.fifty_fifty)
        var fiftyFiftyUsed = true

        nextQuestion()

        friendHelp.setOnClickListener {
            if (friendHelpUsed) {
                Snackbar.make(
                    it,
                    randomAnswer(
                        viewModel.questions[viewModel.currentQuestion].rightAnswer,
                        viewModel.name
                    ),
                    Snackbar.LENGTH_SHORT
                ).show()
                friendHelpUsed = false
                viewModel.usedHints += 1
                friendHelp.setBackgroundColor(Color.GRAY)
                viewModel.points -= 50
            } else Snackbar.make(it, R.string.used, Snackbar.LENGTH_SHORT).show()
        }

        fiftyFifty.setOnClickListener {
            if (fiftyFiftyUsed) {
                fiftyFifty(viewModel.questions[viewModel.currentQuestion].rightAnswer)
                fiftyFiftyUsed = false
                viewModel.usedHints += 1
                fiftyFifty.setBackgroundColor(Color.GRAY)
                viewModel.points -= 50
            } else Snackbar.make(it, R.string.used, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun nextQuestion() {


        if (viewModel.currentQuestion == viewModel.questionsCount) {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.appear, R.anim.disappear)
                .add(R.id.end_screen_container, FragmentEndScreen())
                .commit()
            return
        }

        viewModel.currentQuestion++
        val questionText = findViewById<TextView>(R.id.text_question)

        val answerButtons = arrayOf(
            findViewById<Button>(R.id.button_a),
            findViewById<Button>(R.id.button_b),
            findViewById<Button>(R.id.button_c),
            findViewById<Button>(R.id.button_d)
        )
        val curr = viewModel.currentQuestion
        questionText.text = viewModel.questions[curr].question

        for (i in 0..3) {
            answerButtons[i].visibility = View.VISIBLE
            answerButtons[i].text = viewModel.questions[curr].answers[i]
        }

        val pointsText = findViewById<TextView>(R.id.points)
        pointsText.text = viewModel.points.toString()

        val questionCounterText = findViewById<TextView>(R.id.question_counter)
        questionCounterText.text = viewModel.currentQuestion.toString()
        val questionNumberText = findViewById<TextView>(R.id.question)
        questionNumberText.text = viewModel.questionsCount.toString()


        viewModel.stopTimer()
        viewModel.startTimer()
    }

    private fun readQuestionsFromFile(difficulty: Int) {

        val inputStream: InputStream = resources.openRawResource(R.raw.questions)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        var eachline = bufferedReader.readLine()
        while (eachline != null) {
            val strings = eachline.split(";".toRegex()).toTypedArray()

            if ((strings.size > 5) && (strings[0].toInt() <= difficulty)) {

                val answers = arrayOf(
                    strings[2],
                    strings[3],
                    strings[4],
                    strings[5]
                )
                answers.shuffle()
                var right = 0
                for (i in answers.indices) {
                    if (answers[i] == strings[2]) {
                        right = i
                        break
                    }
                }

                viewModel.questions.add(
                    Question(
                        strings[0].toInt(),
                        strings[1],
                        answers,
                        right
                    )
                )
            }
            eachline = bufferedReader.readLine()
        }
        viewModel.questions.shuffle()
    }

    private fun randomAnswer(rightAns: Int, name: String): String {
        val right = rightAns + 1
        when (Random().nextInt(6)) {
            0 -> return resources.getString(R.string.friend_answer_1).plus(" $right")
            1 -> return resources.getString(R.string.friend_answer_2)
            2 -> return resources.getString(R.string.friend_answer_3)
            3 -> return "Hi $name. Try $right"
            4 -> return "$name are you? Where is my money??"
            5 -> return "You fool, $name ... The easiest question! Answer: $right"
        }
        return "..."
    }

    private fun fiftyFifty(right: Int) {

        val answerButton = arrayOf(
            findViewById<Button>(R.id.button_a),
            findViewById<Button>(R.id.button_b),
            findViewById<Button>(R.id.button_c),
            findViewById<Button>(R.id.button_d)
        )

        for (i in answerButton.indices) {
            answerButton[i].visibility = View.INVISIBLE
        }
        var second = right
        while (second == right) {

            second = Random().nextInt(4)
        }
        answerButton[right].visibility = View.VISIBLE
        answerButton[second].visibility = View.VISIBLE


    }
}