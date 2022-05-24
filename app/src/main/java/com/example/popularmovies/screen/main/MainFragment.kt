package com.example.popularmovies.screen.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.popularmovies.MAIN
import com.example.popularmovies.R
import com.example.popularmovies.databinding.FragmentMainBinding
import com.example.popularmovies.models.MainResult
import java.lang.Exception


class MainFragment : Fragment() {
    //проверяем что биндинг не нал
    private var mBinding: FragmentMainBinding ?= null
    private val binding get() =mBinding !!
    lateinit var recyclerView: RecyclerView

    private val adapter by lazy { MainAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMainBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true) // создае меню в фрагменте
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    private fun init(){
        //доступ к viewModel
        val viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        viewModel.initDatabase() //инизцилизация Базы данных из MainFragmentViewModel
        recyclerView = binding.rcMain //доступ к ресайклеру
        recyclerView.adapter = adapter

        try {


        viewModel.getMoviesRetrofit()
        viewModel.myMovies.observe(viewLifecycleOwner) {
            adapter.setList(it.body()!!.results)
        }
        }catch (e:Exception){
            Toast.makeText(MAIN,e.message,Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
 /// функция для создания Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    // переход по нажатию кнопки меню(сердечко)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.item_fafvorite -> {
                MAIN.navController.navigate(R.id.action_mainFragment_to_favoriteFragment)
                true
            }else -> super.onOptionsItemSelected(item)
        }
    }
    companion object{
    fun onclickView(model:MainResult) {
        // с помощью бандала мы передаем данные между фрагментами
        val bundle = Bundle()
        bundle.putSerializable("movie",model)
        //обязательно нужно передать bundle вторым аргументом
        MAIN.navController.navigate(R.id.action_mainFragment_to_detailFragment,bundle)
        }
    }
}
