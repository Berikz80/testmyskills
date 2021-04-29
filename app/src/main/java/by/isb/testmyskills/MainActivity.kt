package by.isb.testmyskills

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.button_start)

        startButton.setOnClickListener {
            val intent = Intent(this,ActionActivity::class.java)
            startActivity(intent)
        }
    }


}