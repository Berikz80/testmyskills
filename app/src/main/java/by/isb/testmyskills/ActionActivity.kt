package by.isb.testmyskills

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class ActionActivity : AppCompatActivity() {

    lateinit var viewModel: ActionViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action)

        viewModel = ViewModelProvider(this).get(ActionViewModel::class.java)

        readQuestionsFromFile()

        val questionText = findViewById<TextView>(R.id.text_question)
        val answerA = findViewById<Button>(R.id.button_a)
        val answerB = findViewById<Button>(R.id.button_b)
        val answerC = findViewById<Button>(R.id.button_c)
        val answerD = findViewById<Button>(R.id.button_d)

        nextQuestion()

        answerA.setOnClickListener {
            if (viewModel.questions[viewModel.currentQuestion].rightAnswer == 0) {
                Toast.makeText(this, "Right Answer", Toast.LENGTH_SHORT).show()
                viewModel.points++
            }
            else Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
            nextQuestion()

        }

        answerB.setOnClickListener {
            if (viewModel.questions[viewModel.currentQuestion].rightAnswer == 1) {
                Toast.makeText(this, "Right Answer", Toast.LENGTH_SHORT).show()
                viewModel.points++
            }
            else Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
            nextQuestion()

        }

        answerC.setOnClickListener {
            if (viewModel.questions[viewModel.currentQuestion].rightAnswer == 2) {
                Toast.makeText(this, "Right Answer", Toast.LENGTH_SHORT).show()
                viewModel.points++
            }
            else Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
            nextQuestion()
        }

        answerD.setOnClickListener {
            if (viewModel.questions[viewModel.currentQuestion].rightAnswer == 3) {
                Toast.makeText(this, "Right Answer", Toast.LENGTH_SHORT).show()
                viewModel.points++
            }
            else Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
            nextQuestion()
        }

    }

    fun nextQuestion() {
        if (viewModel.currentQuestion==viewModel.questionsCount) {
            Toast.makeText(this, "Bye-Bye", Toast.LENGTH_SHORT).show()
            return
        }
        val questionText = findViewById<TextView>(R.id.text_question)
        val answerA = findViewById<Button>(R.id.button_a)
        val answerB = findViewById<Button>(R.id.button_b)
        val answerC = findViewById<Button>(R.id.button_c)
        val answerD = findViewById<Button>(R.id.button_d)

        var curr = viewModel.currentQuestion
        questionText.text = viewModel.questions[curr].question

        answerA.text = viewModel.questions[curr].answers[0]
        answerB.text = viewModel.questions[curr].answers[1]
        answerC.text = viewModel.questions[curr].answers[2]
        answerD.text = viewModel.questions[curr].answers[3]

        val pointsText = findViewById<TextView>(R.id.points)
        pointsText.text = viewModel.points.toString()

        val questionCounterText = findViewById<TextView>(R.id.question_counter)
        questionCounterText.text = viewModel.currentQuestion.toString()

        viewModel.currentQuestion++

    }

    fun readQuestionsFromFile() {

        val inputStream: InputStream = resources.openRawResource(R.raw.questions)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        var eachline = bufferedReader.readLine()
        while (eachline != null) {
            val words = eachline.split(";".toRegex()).toTypedArray()

            viewModel.questions.add(
                Question(
                    words[0].toInt(),
                    words[1],
                    arrayOf(
                        words[2],
                        words[3],
                        words[4],
                        words[5]
                    ),
                    0
                )
            )

            eachline = bufferedReader.readLine()
        }

    }

}