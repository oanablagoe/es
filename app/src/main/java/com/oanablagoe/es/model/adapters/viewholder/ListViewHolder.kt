package com.oanablagoe.es.model.adapters.viewholder

import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.oanablagoe.es.R
import com.oanablagoe.es.views.list.ListFragment
import com.oanablagoe.es.views.question.Question
import kotlinx.android.synthetic.main.cell_list.view.*

class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val questionTextView: TextView = view.questionTextView
    private val radioGroup: RadioGroup = view.radioGroup
    private val radioA: RadioButton = view.radioA
    private val radioB: RadioButton = view.radioB
    private val radioC: RadioButton = view.radioC
    private val radioD: RadioButton = view.radioD

    private var userGenre = mutableListOf<String>()
    private var userDeleteGenre = mutableListOf<String>()


    fun setup(question: Question, listFragment: ListFragment) {
        questionTextView.text = question.question
        radioA.text = question.answers[0]
        radioB.text = question.answers[1]
        radioC.text = question.answers[2]
        radioD.text = question.answers[3]
        radioGroup.check(R.id.radioD)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioA -> {
                    userGenre = mutableListOf()
                    userGenre.add(radioA.text as String)
                }
                R.id.radioB -> {
                    userGenre = mutableListOf()
                    userGenre.add(radioB.text as String)
                }
                R.id.radioC -> {
                    userGenre = mutableListOf()
                    userGenre.add(radioA.text as String)
                    userGenre.add(radioB.text as String)
                }
                R.id.radioD -> {
                    userGenre = mutableListOf()
                    userDeleteGenre.add(radioA.text as String)
                    userDeleteGenre.add(radioB.text as String)
                }
            }
            userGenre.forEach { title -> listFragment.genreFinal.addAll(listFragment.genre.filter { x -> x.precondition == title && !listFragment.genreFinal.contains(x)} ) }
            userDeleteGenre.forEach { title ->listFragment.genreFinal.removeAll(listFragment.genre.filter { x -> x.precondition == title }) }
        }
    }
}
