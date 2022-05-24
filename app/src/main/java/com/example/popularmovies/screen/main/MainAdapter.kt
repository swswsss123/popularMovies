package com.example.popularmovies.screen.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.popularmovies.BASE_IMG
import com.example.popularmovies.MAIN
import com.example.popularmovies.R
import com.example.popularmovies.models.MainResult
import kotlinx.android.synthetic.main.item_layuout.view.*

class MainAdapter:RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
    //складываем наши фильмы
    private var listMovies = emptyList<MainResult>()

    class MyViewHolder(view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MyViewHolder {
        //положи всю информацию в наш item_layuout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layuout,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainAdapter.MyViewHolder, position: Int) {
        //какие поля из адаптера мы будем заполнять в моем случае 3 поля картинка,название и дата
        holder.itemView.text_Title.text = listMovies[position].title
        holder.itemView.text_Data.text = listMovies[position].release_date
        //через гидле делаем картинку,
        Glide.with(MAIN)
            .load(BASE_IMG+listMovies[position].poster_path)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            //передаем в нашу картинку
            .into(holder.itemView.item_img)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }
    //сюда мы положем все что пришло из MainFragment  и положить все по нашим ячейкам
    fun setList(list:List <MainResult>){
        listMovies = list
        notifyDataSetChanged()
    }
// вся эа функция служит для перехода между экранами при нажатие на Item в RecyclerView
    override fun onViewAttachedToWindow(holder: MyViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            MainFragment.onclickView(listMovies[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MyViewHolder) {
        holder.itemView.setOnClickListener(null)
    }
}