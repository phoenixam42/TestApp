package phoenix.org.testprojectsomething.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import phoenix.org.testprojectsomething.Application.FavoriteMovie
import phoenix.org.testprojectsomething.Repository
import phoenix.org.testprojectsomething.model.Database.Person

class ShowFragmentViewModel(val repository: Repository) : ViewModel() {
    val allDataMovie = repository.getdataMovie
    val allData:LiveData<List<Person>> = repository.allData.asLiveData()

    fun getData(title :String) {
        viewModelScope.launch {
                repository.getDATA(title)
            }
        }
    fun getNamedInfo(name: String): LiveData<List<Person>> {
        return repository.getNamedInfo(name).asLiveData()
    }

     fun deleteFavoriteMovie(favoriteMovie: String,username: String ){
         viewModelScope.launch {
             repository.deleteMovie(favoriteMovie,username)
         }
    }

    val getdata: LiveData<List<Person>> = repository.allData.asLiveData()
    fun inset(person: Person) {
        viewModelScope.launch {
            repository.insert(person)
        }
    }

    class ViewModelFactory(val repository: Repository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ShowFragmentViewModel::class.java)) {
                return ShowFragmentViewModel(repository) as T
            }
            throw IllegalArgumentException("UNKNOWN")
        }

    }


}


