package by.isb.testmyskills

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.lifecycle.ViewModelProvider
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ActionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(ActionViewModel::class.java)
        val startButton = findViewById<Button>(R.id.button_start)
        val inputName =
            findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.input_name)
        val difficultyLevel =
            findViewById<com.google.android.material.slider.Slider>(R.id.difficulty_level)
        val numberQuestions =
            findViewById<com.google.android.material.slider.Slider>(R.id.number_questions)
        val timer =
            findViewById<com.google.android.material.slider.Slider>(R.id.timer)

        startButton.setOnClickListener {
            val intent = Intent(this, ActionActivity::class.java)
            intent.putExtra("name", inputName.text.toString())
            intent.putExtra("difficulty", difficultyLevel.value.toInt())
            intent.putExtra("questions", numberQuestions.value.toInt())
            intent.putExtra("timer", timer.value.toInt())
            startActivity(intent)

        }

    }


}