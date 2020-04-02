package com.oanablagoe.es.views.list

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.oanablagoe.es.R
import com.oanablagoe.es.helper.extension.fromJson
import com.oanablagoe.es.views.question.Genre
import com.oanablagoe.es.views.question.Question
import com.oanablagoe.es.views.question.Result
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.viewDetails

class ListFragment : Fragment() {
    var genre = mutableListOf<Genre>()
    var genreFinal = mutableListOf<Genre>()
    var result = mutableListOf<Result>()
    var resultFinal = mutableListOf<Result>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        genre = readGenre()
        result = readResults()
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        listRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = com.oanablagoe.es.model.adapters.ListAdapter(readQuestions(), this@ListFragment)
            addItemDecoration(DividerItemDecoration(this.context, LinearLayout.VERTICAL))
        }
        invisible()
        buttonGenerate.setOnClickListener {
            genreFinal.forEach { gen ->
                var r = result.filter { x ->
                    gen.postcondition.contains(x.precondition) && !resultFinal.contains(x)
                }
                resultFinal.addAll(r)
            }
            if(resultFinal.size < 2 && result.size > 2){
                genreFinal.forEach { gen ->
                    var r = result.filter { x -> !resultFinal.contains(x) }
                    resultFinal.addAll(r)
                }
            }
            if (resultFinal.size < 2) {
                val myToast = Toast.makeText(context, "Please select more than 3 shows", Toast.LENGTH_SHORT)
                myToast.setGravity(Gravity.LEFT, 200, 200)
                myToast.show()
            } else {
                firstRecommendation.text = resultFinal!![0].postcondition!![0]
                secondRecommendation.text = resultFinal!![1].postcondition!![0]
                thirdRecommendation.text = resultFinal!![2].postcondition!![0]
                viewDetails.visibility = View.VISIBLE
                init()
            }
        }
        viewDetails.setOnClickListener{
            viewDetails.visibility = View.INVISIBLE
            this.onResume()
        }
    }

    private fun init(){
        genre = readGenre()
        result = readResults()
        genreFinal = mutableListOf()
        resultFinal = mutableListOf()
    }

    private fun invisible(){
        viewDetails.visibility = View.INVISIBLE
    }

    private fun readGenre(): MutableList<Genre> {
        val text = resources.openRawResource(R.raw.genre).bufferedReader().use { it.readText() }
        return text.fromJson()
    }

    private fun readQuestions(): List<Question> {
        val text = resources.openRawResource(R.raw.question).bufferedReader().use { it.readText() }
        return text.fromJson()
    }

    private fun readResults(): MutableList<Result> {
        val text = resources.openRawResource(R.raw.results).bufferedReader().use { it.readText() }
        return text.fromJson()
    }

}
