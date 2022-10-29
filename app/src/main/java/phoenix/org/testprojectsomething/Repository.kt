package phoenix.org.testprojectsomething

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.Flow
import phoenix.org.testprojectsomething.model.Database.Person
import phoenix.org.testprojectsomething.model.Database.PersonDao
import phoenix.org.testprojectsomething.network.MovieApi
import phoenix.org.testprojectsomething.network.MovieRequest
import phoenix.org.testprojectsomething.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Repository(val dao: PersonDao) {
    val ArrayList: ArrayList<MovieApi.MovieRespond> = ArrayList()

    val allData: Flow<List<Person>> = dao.getPersonInfo()
    private var _getdataMovie = MutableLiveData<ArrayList<MovieApi.MovieRespond>>()
    var getdataMovie: LiveData<ArrayList<MovieApi.MovieRespond>> = _getdataMovie
    suspend fun insert(person: Person) {
        dao.insert(person)
    }
    fun getNamedInfo(name: String): Flow<List<Person>> {
        return dao.getPersonisedInfo(name)
    }
    suspend fun getDATA(title:String) {
        val api = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        val service: MovieRequest = api.create(MovieRequest::class.java)
        val listCall: Call<MovieApi.MovieRespond> = service.getMovieInfo(
            Constants.API_KEY_NUM, title
        )
        listCall.enqueue(object : Callback<MovieApi.MovieRespond> {
            override fun onResponse(
                call: Call<MovieApi.MovieRespond>,
                response: Response<MovieApi.MovieRespond>
            ) {
                response.body()?.let { if (!ArrayList.contains(it))
                    ArrayList.add(it)
                }
                _getdataMovie.value = ArrayList
                Log.i("onResponse", "onResponse:${ArrayList} ")
            }
            override fun onFailure(call: Call<MovieApi.MovieRespond>, t: Throwable) {
                Log.i("onFailure", "onFailure :${t.message}")
            }
        })

    }
    suspend fun deleteMovie (favoriteMovie: String,username: String){
        dao.deleteBaseOnFavoriteMovie(favoriteMovie,username)
    }

}