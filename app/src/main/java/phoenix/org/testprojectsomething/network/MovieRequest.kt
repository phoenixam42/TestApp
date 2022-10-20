package phoenix.org.testprojectsomething.network

import io.reactivex.rxjava3.core.Single
import phoenix.org.testprojectsomething.util.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRequest {
    @GET("/?")
    fun getMovieInfo(
        @Query(Constants.API_KEY) apikey:String,
        @Query(Constants.TITLE_MOVIE) title:String
    ): Call<MovieApi.MovieRespond>
}