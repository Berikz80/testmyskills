package by.isb.testmyskills

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class FragmentEndScreen : Fragment() {
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
        timeView.text = viewModel.timeFromStart.toString()


        buttonRestart.setOnClickListener {
            requireActivity().finish()
            requireActivity().startActivity(Intent(view.context, ActionActivity::class.java))
        }

        buttonExit.setOnClickListener {
            requireActivity().finish()
        }

    }
}