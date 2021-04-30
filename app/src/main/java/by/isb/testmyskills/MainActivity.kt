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
        val inputName = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.input_name)
        var needTime : Boolean = false
//     таймер
        val timerEnable = findViewById<CheckBox>(R.id.timerOnOff).setOnClickListener{ if (!viewModel.isTimeEnabled) viewModel.isTimeEnabled= true
        else if (viewModel.isTimeEnabled) viewModel.isTimeEnabled = false }
        startButton.setOnClickListener {
            val intent = Intent(this,ActionActivity::class.java)
            intent.putExtra("name", inputName.text)
            startActivity(intent)

        }

    }


}