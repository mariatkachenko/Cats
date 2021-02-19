package com.example.cats.ui
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cats.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@ExperimentalPagingApi
class CatFragment : Fragment(R.layout.fragment_cat) {

    lateinit var rvCatRemote: RecyclerView
    lateinit var catViewModel: CatViewModel
    lateinit var adapter: CatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMembers()
        setUpViews(view)
        fetchCatImagesLiveData()
    }
    private fun fetchCatImagesLiveData() {
        catViewModel.fetchCatImagesLiveData().observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        })
    }

    private fun initMembers() {
        catViewModel = defaultViewModelProviderFactory.create(CatViewModel::class.java)
        adapter = CatAdapter()
    }

    private fun setUpViews(view: View) {
        rvCatRemote = view.findViewById(R.id.rvCatRemote)
        rvCatRemote.layoutManager = GridLayoutManager(context, 2)
        rvCatRemote.adapter = adapter
    }
}