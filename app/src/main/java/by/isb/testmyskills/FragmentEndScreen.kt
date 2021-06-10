package by.isb.testmyskills

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class FragmentEndScreen: Fragment() {
  lateinit var viewModel : ActionViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_end,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ActionViewModel::class.java)
        val percentScore = view.findViewById<TextView>(R.id.percent_score)
        val scoreMark = view.findViewById<TextView>(R.id.score_mark)
val scoreMarkArray = listOf<String>("try better"," try hard","not bad for beginner","well done","excellent")
        when ((viewModel.points/viewModel.questionsCount)*100){
                in 0..20 -> percentScore.setTextColor(Color.RED)
                in 21..40 -> percentScore.setTextColor(Color.RED)

                in 41..60 ->percentScore.setTextColor(Color.rgb(255,69,0))

                    in 61..80 ->percentScore.setTextColor(Color.rgb(255, 255, 0))
                    in 81..100 -> percentScore.setTextColor(Color.GREEN)
            }
    }

}