package phoenix.org.testprojectsomething.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import phoenix.org.testprojectsomething.R
import phoenix.org.testprojectsomething.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        navController = findNavController(R.id.fragment_container)


       // val toolbar = binding?.toolbarMainActivity
     //  setSupportActionBar(toolbar)
       // NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment_container).navigateUp()
    }
}