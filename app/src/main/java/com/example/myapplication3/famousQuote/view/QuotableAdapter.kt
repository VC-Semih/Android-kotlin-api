package com.example.myapplication3.famousQuote.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication3.chuckNorris.model.QuotableUi
import com.example.myapplication3.databinding.ActivityQuotableBinding
import com.example.myapplication3.databinding.ItemQuotableBinding
import com.example.myapplication3.famousQuote.viewModel.QuotableViewModel

val diffUtils = object : DiffUtil.ItemCallback<QuotableUi>() {
    override fun areItemsTheSame(oldItem: QuotableUi, newItem: QuotableUi): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: QuotableUi, newItem: QuotableUi): Boolean {
        return oldItem == newItem
    }
}

class QuotableViewHolder(
    private val binding: ItemQuotableBinding
) : RecyclerView.ViewHolder(binding.root){

    private lateinit var ui: QuotableUi

    fun bind(quotableUi: QuotableUi){
        ui = quotableUi
        binding.itemQuotableQuote.text = quotableUi.content
        binding.itemQuotableAuthor.text = quotableUi.author
        binding.itemQuotableDate.text = quotableUi.dateAdded
    }
}

class QuotableAdapter : ListAdapter<QuotableUi, QuotableViewHolder>(diffUtils){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotableViewHolder {
        return QuotableViewHolder(
            ItemQuotableBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: QuotableViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class QuotableActivity : AppCompatActivity() {
    private lateinit var viewModel: QuotableViewModel
    private lateinit var binding: ActivityQuotableBinding
    private val adapter : QuotableAdapter = QuotableAdapter()
    private val observer = Observer<List<QuotableUi>> {
        adapter.submitList(it)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuotableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[QuotableViewModel::class.java]

        binding.quotableActivityRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        binding.quotableActivityRv.adapter = adapter

        binding.quotableActivityAdd.setOnClickListener {
            viewModel.fetchNewQuote()
        }

        binding.quotableActivityDelete.setOnClickListener {
            viewModel.deleteAll()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.mQuotableLiveData.observe(this, observer)
    }

    override fun onStop() {
        viewModel.mQuotableLiveData.removeObserver(observer)
        super.onStop()
    }
}