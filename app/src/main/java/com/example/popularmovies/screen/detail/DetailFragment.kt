package com.example.popularmovies.screen.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.popularmovies.BASE_IMG
import com.example.popularmovies.BASE_URL
import com.example.popularmovies.MAIN
import com.example.popularmovies.R
import com.example.popularmovies.databinding.FragmentDetailBinding
import com.example.popularmovies.databinding.FragmentFavoriteBinding
import com.example.popularmovies.databinding.FragmentMainBinding
import com.example.popularmovies.models.MainResult
import com.example.popularmovies.screen.favorite.FavoriteFragmentViewModel
import com.example.popularmovies.screen.main.MainAdapter


class DetailFragment : Fragment() {

    //проверяем что биндинг не нал
    private var mBinding: FragmentDetailBinding?= null
    private val binding get() =mBinding !!
    lateinit var curentMovie: MainResult
    var itFavorite = false



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        curentMovie = arguments?.getSerializable("movie") as MainResult  //передаем сюда даные о фильме в curentMovie
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        //доступ к viewModel
        val viewModel = ViewModelProvider(this).get(DetailFragmentViewModel::class.java)
        Glide.with(MAIN)
            .load(BASE_IMG + curentMovie.poster_path)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(binding.imgDetails)
        binding.tvTitle.text = curentMovie.title //передаем title
        binding.tvData.text = curentMovie.release_date
        binding.tvDescription.text = curentMovie.overview

        binding.imgDetailsFavorite.setOnClickListener{
            if(!itFavorite){
                binding.imgDetailsFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                viewModel.insert(curentMovie){}
                itFavorite = true
            }else{
                binding.imgDetailsFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                viewModel.delete(curentMovie){}
                itFavorite = false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

}