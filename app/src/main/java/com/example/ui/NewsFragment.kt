package com.example.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.News
import com.example.ui.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private val viewModel: NewsViewModel by viewModels()
    lateinit var binding: FragmentNewsBinding
    lateinit var newAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getNews()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        observeLiveData()
        setOnButtonClick()
    }

    private fun observeLiveData() {
        viewModel.news.observe(viewLifecycleOwner) {
            newAdapter.submitList(it)
            showLoading(it)
        }

        viewModel.isException.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireContext(), "Ошибка соединения, повторите попытку", Toast.LENGTH_SHORT).show()
                binding.updateButton.visibility = View.VISIBLE
                binding.progress.visibility = View.GONE
            }
        }
    }

    private fun setOnButtonClick() {
        binding.updateButton.setOnClickListener {
            binding.progress.visibility = View.VISIBLE
            viewModel.updateNews()
        }
    }

    private fun showLoading(news: List<News>) {
        if (news.isNotEmpty()) {
            binding.progress.visibility = View.GONE
            binding.updateButton.visibility = View.GONE
        }
    }

    private fun initList() {
        newAdapter = NewsAdapter()
        with(binding.newsList) {
            adapter = newAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
        binding.swipeRefresh.setOnRefreshListener {
            newAdapter.submitList(emptyList())
            viewModel.updateNews()
            binding.swipeRefresh.isRefreshing = false
        }
    }
}