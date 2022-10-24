package phoenix.org.testprojectsomething.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import phoenix.org.testprojectsomething.util.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieServiceViewModel :ViewModel() {
    //   .create(MovieRequest::class.java)
    /*fun getMovieData(): Single<MovieApi.MovieRespond> {
        return api.getMovieInfo(
            "d6a37e43",
            "game of thrones"
        )
    }*/
}