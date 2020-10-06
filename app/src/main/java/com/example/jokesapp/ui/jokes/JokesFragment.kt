package com.example.jokesapp.ui.jokes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jokesapp.R
import com.example.jokesapp.databinding.FragmentJokesBinding

class JokesFragment : Fragment() {

    private lateinit var viewModel: JokesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentJokesBinding  = DataBindingUtil.inflate(
           inflater, R.layout.fragment_jokes, container, false)
        binding.lifecycleOwner = this
        viewModel =
                ViewModelProviders.of(this).get(JokesViewModel::class.java)
        val adapter = JokesListAdapter()
        val manager = LinearLayoutManager(activity)
        binding.jokesRecycler.layoutManager = manager
        binding.jokesRecycler.adapter = adapter

               viewModel.jokes.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        binding.loadButton.setOnClickListener {
            val count = binding.count.text.toString()
            if(count.isNotEmpty()) viewModel.getJokes(count.toInt())
            else Toast.makeText(context,"please enter number of jokes", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}