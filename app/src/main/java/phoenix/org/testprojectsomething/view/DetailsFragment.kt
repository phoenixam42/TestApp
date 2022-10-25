package phoenix.org.testprojectsomething.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import phoenix.org.testprojectsomething.Application.FavoriteMovie
import phoenix.org.testprojectsomething.R
import phoenix.org.testprojectsomething.databinding.FragmentDetailsBinding
import phoenix.org.testprojectsomething.network.MovieApi
import phoenix.org.testprojectsomething.viewmodel.ShowFragmentViewModel


class DetailsFragment : Fragment() {
    private var binding: FragmentDetailsBinding? = null
    var imbdId: String? = ""
    var FilmArray: ArrayList<MovieApi.MovieRespond> = ArrayList()
    val model: ShowFragmentViewModel by viewModels {
        ShowFragmentViewModel.ViewModelFactory((requireActivity().application as FavoriteMovie).repository)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imbdId = arguments?.getString("imbd")
        Toast.makeText(requireActivity(), "${arguments?.getString("imbd")}", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        model.allDataMovie.observe(viewLifecycleOwner) { movieInfo ->
            //SetUIContent(movieInfo, binding!!)
            for (i in movieInfo) {
                while (i.imdbID == imbdId) {
                    binding!!.txtDetailActors.text = i.Actors
                    binding!!.txtDetailAwards.text = i.Awards
                    binding!!.txtDetailCountry.text = i.Country
                    binding!!.txtDetailGenre.text = i.Genre
                    binding!!.txtDetailTitle.text = i.Title
                    binding!!.txtDetailWriter.text = i.Writer
                    binding!!.txtDetailStory.text = i.Plot
                    binding!!.txtDetailRate.text = i.imdbRating
                    Glide
                        .with(requireActivity())
                        .load(i.Poster)
                        .into(binding!!.imgDetailPoster)
                    /*Glide
                        .with(requireActivity())
                        .load(i.Poster)
                        .into(binding.imgDetailPoster)*/
                    break
                }
            }
        }
        return binding!!.root
    }

    private fun SetUIContent(
        movieInfo: ArrayList<MovieApi.MovieRespond>,
        binding: FragmentDetailsBinding
    ) {
        for (i in movieInfo) {
            while (i.imdbID == imbdId) {
                binding.txtDetailActors.text = i.Actors
                binding.txtDetailAwards.text = i.Awards
                binding.txtDetailCountry.text = i.Country
                binding.txtDetailGenre.text = i.Genre
                binding.txtDetailTitle.text = i.Title
                Glide
                    .with(requireActivity())
                    .load(i.Poster)
                    .into(binding.imgDetailPoster)
                /*Glide
                    .with(requireActivity())
                    .load(i.Poster)
                    .into(binding.imgDetailPoster)*/
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = binding?.toolbarDetailFragment
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.chooseFragment,
                R.id.showFragment,
                R.id.detailsFragment

            )
        )
        if (toolbar != null) {
            NavigationUI.setupWithNavController(toolbar, findNavController())
        }
                toolbar?.title = "Movie Info"


    }


}



