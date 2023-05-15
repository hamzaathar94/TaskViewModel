package com.example.taskviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding:ActivityMainBinding?=null
    private var viewManager = LinearLayoutManager(this)
    private var viewModel: MainViewModel?=null
    private var mainrecycler: RecyclerView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        mainrecycler =binding?.receclerview
        val factory = MainViewModelFactory()
        viewModel=ViewModelProvider(this,factory).get(MainViewModel::class.java)

        binding?.btnsave?.setOnClickListener {
            addData()
        }
        initialiseAdapter()
    }

    private fun initialiseAdapter(){
        mainrecycler?.layoutManager = viewManager
        observeData()
    }

    fun observeData(){
        viewModel?.lst?.observe(this, Observer{
           // Log.i("data",it.toString())
            mainrecycler?.adapter= BlogAdapter(viewModel!!, this,it)
        })
    }


    fun addData(){
        var title = binding?.edtxttitle?.text.toString()
        var author = binding?.edtxtauthor?.text.toString()
        if(binding?.edtxttitle?.text.isNullOrBlank() || binding?.edtxtauthor?.text.isNullOrBlank()){
            Toast.makeText(this,"Enter value!",Toast.LENGTH_LONG).show()
        }else{
            var blog= Blog(title,author)
            viewModel?.add(blog)
            binding?.edtxttitle?.text?.clear()
            binding?.edtxtauthor?.text?.clear()
            mainrecycler?.adapter?.notifyDataSetChanged()
        }

    }
}