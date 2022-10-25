package phoenix.org.testprojectsomething.view

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import phoenix.org.testprojectsomething.Application.FavoriteMovie
import phoenix.org.testprojectsomething.R
import phoenix.org.testprojectsomething.databinding.FragmentShowBinding
import phoenix.org.testprojectsomething.network.MovieApi
import phoenix.org.testprojectsomething.view.adapter.ShowFragmentAdapter
import phoenix.org.testprojectsomething.viewmodel.ShowFragmentViewModel

class ShowFragment : Fragment() {
    var title: ArrayList<String> = ArrayList()
    var adapter: RecyclerView.Adapter<ShowFragmentAdapter.ViewHolder>? = null
    val model: ShowFragmentViewModel by viewModels {
        ShowFragmentViewModel.ViewModelFactory((requireActivity().application as FavoriteMovie).repository)
    }
    private var binding: FragmentShowBinding? = null
    var userName: String? = null
    var userNameAdded: String? = null
    private var mlist: ArrayList<MovieApi.MovieRespond>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userNameAdded = arguments?.getString("userName")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        binding = FragmentShowBinding.inflate(inflater, container, false)
        binding?.fabShowFragment?.setOnClickListener {
            findNavController().navigate(R.id.action_showFragment_to_chooseFragment)

        }
        val toolbar = binding?.toolbarShowFragment
        if (toolbar != null) {
            val appBarConfiguration = AppBarConfiguration(findNavController().graph)
            (activity as AppCompatActivity).setSupportActionBar(toolbar)
            setupActionBarWithNavController(activity as AppCompatActivity,findNavController(),appBarConfiguration)
            toolbar.setupWithNavController(findNavController())

        }
        userName = if (userNameAdded != null && userNameAdded != userName) {
            userNameAdded

        } else {
            "ali"
        }
        try {
            getCustomisedDataAndShowThem()
        } catch (e: Exception) {
            Toast.makeText(requireActivity(), " the is a problem named : $e", Toast.LENGTH_SHORT)
                .show()
        }
        Toast.makeText(requireActivity(), "USER is $userName", Toast.LENGTH_SHORT).show()
        return binding!!.root
    }

    private fun getCustomisedDataAndShowThem() {
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
                        if (i.Response == "True") {
                            m2list.add(i)
                        }
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
                    binding?.rvShowFragment?.isNestedScrollingEnabled = false
                    binding?.nothingText?.visibility = View.INVISIBLE
                    //   Log.i("texc", "onCreateView: ${mlist!![0]}")
                }
                //  Log.i(TAG, "test one: $title")
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.actionbar_menu,menu)

    }


    @Deprecated("Deprecated in Java", ReplaceWith(
        "super.onOptionsItemSelected(item)",
        "androidx.fragment.app.Fragment"
    )
    )
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.item_menu_filter ->  {
                Toast.makeText(requireActivity(), " filter is clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else->{
                return super.onOptionsItemSelected(item)
            }

        }

    }

}


