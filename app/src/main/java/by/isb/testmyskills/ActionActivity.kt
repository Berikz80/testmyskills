package by.isb.testmyskills

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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
        }

        readQuestionsFromFile()

        val questionText = findViewById<TextView>(R.id.text_question)
        val answerA = findViewById<Button>(R.id.button_a)
        val answerB = findViewById<Button>(R.id.button_b)
        val answerC = findViewById<Button>(R.id.button_c)
        val answerD = findViewById<Button>(R.id.button_d)

        val friendHelp = findViewById<Button>(R.id.call_friend)
        var useFriendHelp = true


        nextQuestion()

        answerA.setOnClickListener {
            if (viewModel.questions[viewModel.currentQuestion].rightAnswer == 0) {
                Toast.makeText(this, "Right Answer", Toast.LENGTH_SHORT).show()
                viewModel.points++
            } else Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
            nextQuestion()

        }

        answerB.setOnClickListener {
            if (viewModel.questions[viewModel.currentQuestion].rightAnswer == 1) {
                Toast.makeText(this, "Right Answer", Toast.LENGTH_SHORT).show()
                viewModel.points++
            } else Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
            nextQuestion()
        }

        answerC.setOnClickListener {
            if (viewModel.questions[viewModel.currentQuestion].rightAnswer == 2) {
                Toast.makeText(this, "Right Answer", Toast.LENGTH_SHORT).show()
                viewModel.points++
            } else Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
            nextQuestion()
        }

        answerD.setOnClickListener {
            if (viewModel.questions[viewModel.currentQuestion].rightAnswer == 3) {
                Toast.makeText(this, "Right Answer", Toast.LENGTH_LONG).show()
                viewModel.points++
            } else Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
            nextQuestion()
        }

        friendHelp.setOnClickListener {
            if (useFriendHelp) {
                Snackbar.make(it, randomAnswer(viewModel.questions[viewModel.currentQuestion].rightAnswer,viewModel.name), Snackbar.LENGTH_SHORT).show()
                useFriendHelp=false
                friendHelp.setBackgroundColor(Color.GRAY)
            }else Snackbar.make(it,R.string.used,Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun nextQuestion() {

        if (viewModel.currentQuestion == viewModel.questionsCount) {
            MaterialAlertDialogBuilder(this)
                .setTitle(resources.getString(R.string.end_game))
                .setMessage(resources.getString(R.string.total_score).plus(" ${viewModel.points}"))
                .setNegativeButton(resources.getString(R.string.exit)) { _, _ ->
                    super.finish()
                }
                .setPositiveButton(resources.getString(R.string.restart)) { _, _ ->
                    super.finish()
                    startActivity(intent)
                }
                .show()
            return

        }
        viewModel.currentQuestion++
        val questionText = findViewById<TextView>(R.id.text_question)
        val answerA = findViewById<Button>(R.id.button_a)
        val answerB = findViewById<Button>(R.id.button_b)
        val answerC = findViewById<Button>(R.id.button_c)
        val answerD = findViewById<Button>(R.id.button_d)

        val curr = viewModel.currentQuestion
        questionText.text = viewModel.questions[curr].question

        answerA.text = viewModel.questions[curr].answers[0]
        answerB.text = viewModel.questions[curr].answers[1]
        answerC.text = viewModel.questions[curr].answers[2]
        answerD.text = viewModel.questions[curr].answers[3]

        val pointsText = findViewById<TextView>(R.id.points)
        pointsText.text = viewModel.points.toString()

        val questionCounterText = findViewById<TextView>(R.id.question_counter)
        questionCounterText.text = viewModel.currentQuestion.toString()
        val questionNumberText = findViewById<TextView>(R.id.question)
        questionNumberText.text = viewModel.questionsCount.toString()

    }

    private fun readQuestionsFromFile() {

        val inputStream: InputStream = resources.openRawResource(R.raw.questions)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        var eachline = bufferedReader.readLine()
        while (eachline != null) {
            val strings = eachline.split(";".toRegex()).toTypedArray()

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

            eachline = bufferedReader.readLine()
        }

        viewModel.questions.shuffle()
    }
    fun randomAnswer (rightAns: Int, name:String): String {
        val right = rightAns + 1
        when(Random().nextInt(5)){
            0 -> return resources.getString(R.string.friend_answer_1).plus(" $right")
            1 -> return resources.getString(R.string.friend_answer_2)
            2 -> return resources.getString(R.string.friend_answer_3)
            3 -> return "Hi $name. Try $right"
            4 -> return "$name are you? Where is my money??"
            5 -> return "You fool, $name ... The easiest question! Answer: $right"
        }
        return "..."
    }

}