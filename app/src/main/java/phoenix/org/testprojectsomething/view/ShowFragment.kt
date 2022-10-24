package phoenix.org.testprojectsomething.view

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import phoenix.org.testprojectsomething.Application.FavoriteMovie
import phoenix.org.testprojectsomething.R
import phoenix.org.testprojectsomething.databinding.FragmentShowBinding
import phoenix.org.testprojectsomething.network.MovieApi
import phoenix.org.testprojectsomething.view.adapter.ShowFragmentAdapter
import phoenix.org.testprojectsomething.viewmodel.ShowFragmentViewModel

class ShowFragment : Fragment(), ShowFragmentAdapter.OnClickListener {
    var title: ArrayList<String> = ArrayList()
    var adapter: RecyclerView.Adapter<ShowFragmentAdapter.ViewHolder>? = null
    val model: ShowFragmentViewModel by viewModels {
        ShowFragmentViewModel.ViewModelFactory((requireActivity().application as FavoriteMovie).repository)
    }
    private var binding: FragmentShowBinding? = null
    var userName: String? = null
    private var mlist: ArrayList<MovieApi.MovieRespond>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userName = arguments?.getString("userName")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowBinding.inflate(inflater, container, false)
        binding?.fabShowFragment?.setOnClickListener {
            findNavController().navigate(R.id.action_showFragment_to_chooseFragment)

        }
        if (userName == null) {
            userName = "ali"
        }
        Toast.makeText(requireActivity(),"USER is $userName",Toast.LENGTH_SHORT).show()
        model.getNamedInfo(userName!!).observe(viewLifecycleOwner) { getname ->
            getname.let {
                for (i in getname) {
                    if (i.favoriteMovie.isNotEmpty()) {
                        title.add(i.favoriteMovie)
                    }
                }
            }
            Log.i(TAG, "test one: $title")

            lifecycleScope.launch {
                model.allData.observe(viewLifecycleOwner) { alldata ->
                    if (alldata.isNotEmpty()) {
                        if (title.isEmpty()) {
                            title.add("the lost")
                        }
                        for (i in title) {
                            model.getData(i)
                        }
                    }
                }
            }
            model.allDataMovie.observe(viewLifecycleOwner) { movieinfo ->
                val m2list: ArrayList<MovieApi.MovieRespond> = ArrayList()
                movieinfo?.let { respond ->
                    for (i in respond) {
                        m2list.add(i)
                    }
                    mlist = m2list
                    adapter = mlist?.let { ShowFragmentAdapter(it, requireActivity()) }
                    //   Toast.makeText(requireActivity(), "$mlist", Toast.LENGTH_LONG).show()
                    binding?.rvShowFragment?.adapter = adapter
                    Log.i(TAG, "onCreateView 1 : ${mlist}")
                    Log.i(TAG, "onCreateView size1 : ${title.size}")
                    binding?.rvShowFragment?.layoutManager =
                        GridLayoutManager(requireActivity(), 2)
                    binding?.rvShowFragment?.visibility = View.VISIBLE
                    binding?.nothingText?.visibility = View.INVISIBLE
                    //   Log.i("texc", "onCreateView: ${mlist!![0]}")
                }
                //  Log.i(TAG, "test one: $title")
            }
        }
        return binding!!.root
    }

    override fun onclick(position: Int) {

    }
}


