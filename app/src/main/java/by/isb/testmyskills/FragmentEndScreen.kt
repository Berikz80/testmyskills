package by.isb.testmyskills


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider


class FragmentEndScreen : Fragment() {
    lateinit var viewModel: ActionViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_end, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity()).get(ActionViewModel::class.java)

        val buttonRestart = view.findViewById<Button>(R.id.restart_button)
        val buttonExit = view.findViewById<Button>(R.id.exit_button)

        val pointsView = view.findViewById<TextView>(R.id.points)
        val usedHintView = view.findViewById<TextView>(R.id.used_hints)
        val timeView = view.findViewById<TextView>(R.id.time_from_start)
        val numberOfQuestionsView = view.findViewById<TextView>(R.id.number_of_questions)

        pointsView.text = viewModel.points.toString()
        usedHintView.text = viewModel.usedHints.toString()
        numberOfQuestionsView.text = viewModel.questionsCount.toString()
        timeView.text = viewModel.timeFromStart.toString().plus(" sec")

        viewModel.timeFromStart = 0


        buttonRestart.setOnClickListener {
            requireActivity().finish()
            requireActivity().startActivity(Intent(view.context, ActionActivity::class.java))
        }

        buttonExit.setOnClickListener {
            requireActivity().finish()
        }

        val percentScore = view.findViewById<TextView>(R.id.percent_score)
        val scoreMark = view.findViewById<TextView>(R.id.score_mark)
        val scoreMarkArray = listOf(
            "Try better",
            "Try hard",
            "Not bad for beginner",
            "Well done",
            "Excellent"
        )

        when (val mark = (viewModel.points / viewModel.questionsCount) * 100) {
            in 0..20 -> {
                percentScore.setTextColor(Color.BLACK)
                scoreMark.text = scoreMarkArray[0]
                percentScore.text = mark.toString().plus("%")
            }
            in 21..40 -> {
                percentScore.setTextColor(Color.RED)
                scoreMark.text = scoreMarkArray[1]
                percentScore.text = mark.toString().plus("%")
            }
            in 41..60 -> {
                percentScore.setTextColor(Color.rgb(255, 69, 0))
                scoreMark.text = scoreMarkArray[2]
                percentScore.text = mark.toString().plus("%")
            }
            in 61..80 -> {
                percentScore.setTextColor(Color.rgb(255, 255, 0))
                scoreMark.text = scoreMarkArray[3]
                percentScore.text = mark.toString().plus("%")
            }
            in 81..100 -> {
                percentScore.setTextColor(Color.GREEN)
                scoreMark.text = scoreMarkArray[4]
                percentScore.text = mark.toString().plus("%")
            }
        }

    }

}