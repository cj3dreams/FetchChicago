package com.cj3dreams.fetchchicago.view.ui.sortedrest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cj3dreams.fetchchicago.R
import com.cj3dreams.fetchchicago.model.response.FetchListResponseItem
import com.cj3dreams.fetchchicago.view.adapter.FetchListAdapter
import com.cj3dreams.fetchchicago.vm.SortedRestViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SortedRestFragment : Fragment() {
    private lateinit var recyclerViewRest: RecyclerView
    private val sortedRestViewModel: SortedRestViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = layoutInflater.inflate(R.layout.fragment_sorted_rest, container, false)
        recyclerViewRest = view.findViewById(R.id.recyclerViewRest)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewRest.layoutManager = LinearLayoutManager(requireContext())
        sortedRestViewModel.fetchListLiveData.observe(viewLifecycleOwner, {
            recyclerViewRest.adapter = FetchListAdapter(it!!)
        })
        sortedRestViewModel.getFetchListFromRemote()

    }

}