package com.cj3dreams.fetchchicago.view.ui.sortedrest

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cj3dreams.fetchchicago.R
import com.cj3dreams.fetchchicago.view.adapter.FetchListAdapter
import com.cj3dreams.fetchchicago.vm.SortedRestViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SortedRestFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var recyclerViewRest: RecyclerView
    private lateinit var swipeToUpdateRest: SwipeRefreshLayout
    private lateinit var progressBarRest: ProgressBar
    private val sortedRestViewModel: SortedRestViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sortedRestViewModel.getFetchListFromRemoteAndSort()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = layoutInflater.inflate(R.layout.fragment_sorted_rest, container, false)
        swipeToUpdateRest = view.findViewById(R.id.swipeToUpdateRest)
        progressBarRest = view.findViewById(R.id.progressBarRest)
        recyclerViewRest = view.findViewById(R.id.recyclerViewRest)
        recyclerViewRest.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeToUpdateRest.setOnRefreshListener(this)
        sortedRestViewModel.fetchListLiveData.observe(viewLifecycleOwner, {

            when {
                it == null -> {
                    progressBarRest.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), "Something has gone wrong", Toast.LENGTH_SHORT).show()
                }
                it.isEmpty() -> progressBarRest.visibility = View.VISIBLE
                else -> {
                    recyclerViewRest.adapter = FetchListAdapter(it)
                    progressBarRest.visibility = View.GONE
                }
            }
        })
    }

    override fun onRefresh() {
        sortedRestViewModel.getFetchListFromRemoteAndSort()
        swipeToUpdateRest.isRefreshing = false
    }
}