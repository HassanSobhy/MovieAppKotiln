package com.example.moviesapp

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviesapp.network.Results
import com.example.moviesapp.ui.home.MovieAdapter


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Results>?) {

    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, posterPath: String?) {
    posterPath?.let {
        val baseUri = Uri.parse("http://image.tmdb.org/t/p/w780/")
        val imgUri = Uri.withAppendedPath(baseUri, posterPath).buildUpon().build()
        //val imgUri =imgUrl.toUri("").buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("movieTitle")
fun bindTitle(textView: TextView, movieTitle: String?) {
    movieTitle?.let {
        textView.text = it
    }
}

@BindingAdapter("movieReleaseDate")
fun bindReleaseDate(textView: TextView, releaseDate: String?) {
    releaseDate?.let {
        textView.text = it
    }
}


@BindingAdapter("movieVoteAvg")
fun bindVoteAvg(textView: TextView, voteAvg: String?) {
    voteAvg?.let {
        textView.text = it
    }
}

@BindingAdapter("movieOverView")
fun bindOverView(textView: TextView, overView: String?) {
    overView?.let {
        textView.text = it
    }
}