package phoenix.org.testprojectsomething.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch
import phoenix.org.testprojectsomething.Repository
import phoenix.org.testprojectsomething.model.Database.Person
import phoenix.org.testprojectsomething.network.MovieApi

class ShowFragmentViewModel(val repository: Repository) : ViewModel() {
    val allDataMovie = repository.getdataMovie
    val allData:LiveData<List<Person>> = repository.allData.asLiveData()

    fun getData(title :String) {
        viewModelScope.launch {
                repository.getDATA(title)
            }
        }
    fun getNamedInfo(name:String):LiveData<List<Person>>{
        val namedData: LiveData<List<Person>> = repository.getNamedInfo(name).asLiveData()
        return namedData
    }

    val getdata: LiveData<List<Person>> = repository.allData.asLiveData()
    fun inset(person: Person) {
        viewModelScope.launch {
            repository.insert(person)
        }
    }

    val movieInfo: MutableLiveData<MovieApi.MovieRespond> =
        MovieViewModel().movieInfoRespond
    val movieError: MutableLiveData<Boolean> =
        MovieViewModel().movieInfoError
    val movieLoading: MutableLiveData<Boolean> =
        MovieViewModel().loadMovieInfo


    class ViewModelFactory(val repository: Repository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ShowFragmentViewModel::class.java)) {
                return ShowFragmentViewModel(repository) as T
            }
            throw IllegalArgumentException("UNKNOWN")
        }

    }


}


