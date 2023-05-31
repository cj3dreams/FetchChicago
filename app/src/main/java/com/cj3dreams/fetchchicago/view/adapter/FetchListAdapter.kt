package com.cj3dreams.fetchchicago.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cj3dreams.fetchchicago.R
import com.cj3dreams.fetchchicago.model.FetchListModel

class FetchListAdapter(
    private val list: List<FetchListModel>
    ): RecyclerView.Adapter<FetchListAdapter.FetchListViewHolder>(){

    class FetchListViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val itemIdTx = view.findViewById(R.id.itemIdTx) as TextView
        val itemListIdTx = view.findViewById(R.id.itemListIdTx) as TextView
        val itemNameTx = view.findViewById(R.id.itemNameTx) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FetchListViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.fetch_item, parent, false)

        return FetchListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FetchListViewHolder, position: Int) {
        val itemData = list[position]
        holder.itemIdTx.text = itemData.id.toString()
        holder.itemListIdTx.text = itemData.listId.toString()
        holder.itemNameTx.text = itemData.name

    }

    override fun getItemCount() = list.size

}
