package by.isb.testmyskills

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ActionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

  //  viewModel = ViewModelProvider(this).get(ActionViewModel::class.java)

    fun readQuestionsFromFile() {

        val inputStream: InputStream = getResources().openRawResource(R.raw.questions)
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        var eachline = bufferedReader.readLine()
        while (eachline != null) {
            val words = eachline.split(" ".toRegex()).toTypedArray()

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