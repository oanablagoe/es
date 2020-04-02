package com.oanablagoe.es.model.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.oanablagoe.es.R
import com.oanablagoe.es.model.adapters.viewholder.ListViewHolder
import com.oanablagoe.es.views.list.ListFragment
import com.oanablagoe.es.views.question.Question

class ListAdapter(private val list: List<Question>, private val listFragment: ListFragment): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cell_list, parent, false))
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ListViewHolder).setup(list[position], listFragment)
    }
}
