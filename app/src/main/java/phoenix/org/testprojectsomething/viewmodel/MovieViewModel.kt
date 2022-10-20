package phoenix.org.testprojectsomething.viewmodel

import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.disposables.CompositeDisposable
import phoenix.org.testprojectsomething.network.MovieApi
import phoenix.org.testprojectsomething.network.MovieServiceViewModel

class MovieViewModel  {
    private val movieInfo = MovieServiceViewModel()
    private val compositeDisposable = CompositeDisposable()
    val loadMovieInfo: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val movieInfoRespond = MutableLiveData<MovieApi.MovieRespond>()
    val movieInfoError = MutableLiveData<Boolean>()

  /*  fun getMovieInfoFromApi(){
        loadMovieInfo.value = true
        compositeDisposable.add(
                    movieInfo.getMovieData().subscribeOn(
                Schedulers.newThread()
            ).observeOn(AndroidSchedulers.mainThread()).subscribeWith(object :
                DisposableSingleObserver<MovieApi.MovieRespond>() {
                override fun onSuccess(t: MovieApi.MovieRespond) {
                    Log.i("onSuccess", "onSuccess: $t")
                    loadMovieInfo.value = false
                    movieInfoRespond.value = t
                    movieInfoError.value = false
                }
                override fun onError(e: Throwable) {
                    Log.i("onError", "onError: $e")
                    loadMovieInfo.value = false
                    movieInfoError.value = true
                    e.printStackTrace()
                }
            })
        )
    }*/
}
