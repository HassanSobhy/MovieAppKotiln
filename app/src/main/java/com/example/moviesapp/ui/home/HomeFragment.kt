package com.example.moviesapp.ui.home


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    /**
     * Lazily initialize our [HomeViewModel].
     */
    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this).get(HomeViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater)

        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        binding.recyclerView.adapter =
            MovieAdapter(MovieAdapter.OnClickListener {
                viewModel.displayMovieDetails(it)
            })

        viewModel.navigateToSelectedMovie.observe(this, Observer {
            if (it != null) {
                this.findNavController()
                    .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it))
                viewModel.displayMovieDetailsComplete()
            }
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.topRated -> viewModel.getTopRatedMoviesResponse()
            R.id.mostPopular -> viewModel.getPopularMoviesResponse()
        }
        return true
    }

}
