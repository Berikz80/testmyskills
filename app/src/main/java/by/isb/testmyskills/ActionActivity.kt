package by.isb.testmyskills

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class ActionActivity : AppCompatActivity() {

    lateinit var viewModel: ActionViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ActionViewModel::class.java)

    }

    fun nextQuestion() {

        val questionText = findViewById<TextView>(R.id.text_question)
        val answerA = findViewById<Button>(R.id.button_a)
        val answerB = findViewById<Button>(R.id.button_b)
        val answerC = findViewById<Button>(R.id.button_c)
        val answerD = findViewById<Button>(R.id.button_d)

        questionText.text = viewModel.questions[viewModel.currentQuestion].question

        answerA.text = viewModel.questions[viewModel.currentQuestion].answer1
        answerB.text = viewModel.questions[viewModel.currentQuestion].answer2
        answerC.text = viewModel.questions[viewModel.currentQuestion].answer3
        answerD.text = viewModel.questions[viewModel.currentQuestion].answerRight

    }

    fun readQuestionsFromFile() {

        val inputStream: InputStream = getResources().openRawResource(R.raw.questions)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        var eachline = bufferedReader.readLine()
        while (eachline != null) {
            val words = eachline.split(";".toRegex()).toTypedArray()

            viewModel.questions.add(
                Question(
                    words[0].toInt(),
                    words[1],
                    words[2],
                    words[3],
                    words[4],
                    words[5]
                )
            )

            eachline = bufferedReader.readLine()
        }

    }

}