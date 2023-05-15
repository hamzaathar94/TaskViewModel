package com.example.taskviewmodel

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskviewmodel.databinding.ItemViewBinding

class BlogAdapter(val viewModel: MainViewModel,var context: Context, val data: List<Blog>)
    :RecyclerView.Adapter<BlogAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogAdapter.MyViewHolder {
        val binding=ItemViewBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BlogAdapter.MyViewHolder, position: Int) {
        val std=data[position]
        holder.binding.textView.text=std.title.toString()
        holder.binding.textView2.text=std.author.toString()

        holder.binding.imageView.setOnClickListener {
            viewModel.remove(std)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class MyViewHolder(var binding:ItemViewBinding):RecyclerView.ViewHolder(binding.root){

    }

}