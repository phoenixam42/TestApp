package phoenix.org.testprojectsomething.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import phoenix.org.testprojectsomething.Application.FavoriteMovie
import phoenix.org.testprojectsomething.databinding.FragmentChooseBinding
import phoenix.org.testprojectsomething.model.Database.Person
import phoenix.org.testprojectsomething.viewmodel.ShowFragmentViewModel

class ChooseFragment : Fragment() {

    private var binding: FragmentChooseBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val model: ShowFragmentViewModel by viewModels {
            ShowFragmentViewModel.ViewModelFactory((requireActivity().application as FavoriteMovie).repository)
        }
        binding = FragmentChooseBinding.inflate(inflater, container, false)

        binding?.btnSaveShowFragment?.setOnClickListener {
            val name = binding?.edtName?.text.toString()
            val favoriteMovie = binding?.edtMovie?.text.toString()
            model.inset(Person(name = name, favoriteMovie = favoriteMovie))
            Toast.makeText(
                requireActivity().applicationContext,
                "your data saved successfully movie :  $favoriteMovie",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().popBackStack()
        }
        // Inflate the layout for this fragment
        return binding?.root
    }


}