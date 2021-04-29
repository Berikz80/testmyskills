package by.isb.testmyskills

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val start = findViewById<Button>(R.id.button_start)
        val name = findViewById<TextInputLayout>(R.id.input_name)

        start.setOnClickListener {
            startActivity(Intent(this,ActionActivity::class.java))
        }
    }



}