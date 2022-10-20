package phoenix.org.testprojectsomething.view

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.launch
import phoenix.org.testprojectsomething.Application.FavoriteMovie
import phoenix.org.testprojectsomething.R
import phoenix.org.testprojectsomething.databinding.FragmentShowBinding
import phoenix.org.testprojectsomething.network.MovieApi
import phoenix.org.testprojectsomething.view.adapter.ShowFragmentAdapter
import phoenix.org.testprojectsomething.viewmodel.ShowFragmentViewModel


class ShowFragment : Fragment() {
    var title: ArrayList<String> = ArrayList()
    val model: ShowFragmentViewModel by viewModels {
        ShowFragmentViewModel.ViewModelFactory((requireActivity().application as FavoriteMovie).repository)
    }
    private var binding: FragmentShowBinding? = null
    var mtitle: ArrayList<String> = ArrayList()
    private var mlist: ArrayList<MovieApi.MovieRespond>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowBinding.inflate(inflater, container, false)
        binding?.fabShowFragment?.setOnClickListener {
            findNavController().navigate(R.id.action_showFragment_to_chooseFragment)
        }
        model.getNamedInfo("ali").observe(viewLifecycleOwner) {
            it.let {
                for (i in it){
                    title.add(i.favoriteMovie)
                }
            }
        }
        lifecycleScope.launch {
            model.allData.observe(viewLifecycleOwner) {
                if(it.isNotEmpty()){
                    val m = title.toSet()
                    for (i in m){
                        model.getData(i)
                    }
                    // model.getData("breaking bad")
                    //   title = it.last().favoriteMovie.toString()
                  //  Log.i(TAG, "readDatabase: ${it.last().favoriteMovie}")
                }
            }
        }
        model.allDataMovie.observe(viewLifecycleOwner) { movieinfo ->
            val m2list:ArrayList<MovieApi.MovieRespond> = ArrayList()
            movieinfo?.let {
                for (i in it) {
                    m2list.add(i)
                    mlist = m2list
                }
                binding?.rvShowFragment?.adapter =
                    mlist?.let { ShowFragmentAdapter(mlist!!, requireActivity()) }
                Log.i(TAG, "onCreateView size : ${mlist?.size}")
                Log.i(TAG, "onCreateView size1 : ${title.size}")
                binding?.rvShowFragment?.layoutManager = GridLayoutManager(requireActivity(), 2)
                binding?.rvShowFragment?.visibility = View.VISIBLE
                binding?.nothingText?.visibility = View.INVISIBLE
                //   Log.i("texc", "onCreateView: ${mlist!![0]}")
            }
        }
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}


