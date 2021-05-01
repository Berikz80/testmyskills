package by.isb.testmyskills

import android.graphics.Color
import android.graphics.ColorSpace
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate


class HelpFragment : Fragment() {


    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(ActionViewModel::class.java) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_help, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
            val graph = view.findViewById<com.github.mikephil.charting.charts.BarChart>(R.id.graphic)
        val array = arrayListOf<BarEntry>()
        array.add(0,BarEntry(1.toFloat(),viewModel.percentageOfAAnswer.toFloat()))
        array.add(1, BarEntry(2.toFloat(),viewModel.percentageOfBAnswer.toFloat()))
        array.add(2, BarEntry(2.toFloat(),viewModel.percentageOfCAnswer.toFloat()))
        array.add(3, BarEntry(2.toFloat(),viewModel.percentageOfDAnswer.toFloat()))
       val barDataSet =  BarDataSet(array,"people think...")
        barDataSet.setColors(ColorTemplate.COLOR_NONE)
        val barData = BarData(barDataSet)
        graph.setFitBars(true)
        graph.data = barData

    }




}