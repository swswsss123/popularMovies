package com.example.popularmovies.screen.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.MAIN
import com.example.popularmovies.R
import com.example.popularmovies.databinding.FragmentFavoriteBinding
import com.example.popularmovies.databinding.FragmentMainBinding
import com.example.popularmovies.models.MainResult
import com.example.popularmovies.screen.main.MainAdapter
import com.example.popularmovies.screen.main.MainFragmentViewModel


class FavoriteFragment : Fragment() {

    //проверяем что биндинг не нал
    private var mBinding: FragmentFavoriteBinding ?= null
    private val binding get() =mBinding !!
    lateinit var recyclerView: RecyclerView
    private val adapter by lazy { FavoriteAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentFavoriteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        //доступ к viewModel
        val viewModel = ViewModelProvider(this).get(FavoriteFragmentViewModel::class.java)
        recyclerView = binding.rcFavorite //доступ к ресайклеру
        recyclerView.adapter = adapter
        viewModel.getAllMovies().observe(viewLifecycleOwner) { it ->
            adapter.setList(it.asReversed())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
    companion object{
        fun onclickView(model: MainResult) {
            // с помощью бандала мы передаем данные между фрагментами
            val bundle = Bundle()
            bundle.putSerializable("movie",model)
            //обязательно нужно передать bundle вторым аргументом
            MAIN.navController.navigate(R.id.action_favoriteFragment_to_detailFragment,bundle)
        }
    }

}