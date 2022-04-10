package com.mestory.test.domain.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mestory.test.databinding.ItemResultBinding
import com.mestory.test.model.entities.GoogleSearchOrganicResultEntity

class ResultAdapter(private val resultsList: List<GoogleSearchOrganicResultEntity>): RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val itemResultBinding = ItemResultBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ResultViewHolder(itemResultBinding)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(resultsList[position])
    }

    override fun getItemCount(): Int {
        return resultsList.size
    }
    inner class ResultViewHolder(private val itemResultBinding: ItemResultBinding) : RecyclerView.ViewHolder(itemResultBinding.root){
        fun bind(resultEntity: GoogleSearchOrganicResultEntity) {
            itemResultBinding.tvId.text = resultEntity.id.toString()
            itemResultBinding.tvCategory.text = resultEntity.category?.name
            itemResultBinding.tvLink.text = resultEntity.link
            itemResultBinding.tvDate.text = resultEntity.date
        }
    }
}