package phoenix.org.testprojectsomething.network

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
object MovieApi : Parcelable {
    @Parcelize
    data class MovieRespond(
        val Actors: String,
        val Awards: String,
        val Country: String,
        val Director: String,
        val Genre: String,
        val Language: String,
        val Metascore: String,
        val Plot: String,
        val Poster: String,
        val Rated: String,
        val Ratings: List<Rating>,
        val Released: String,
        val Response: String,
        val Runtime: String,
        val Title: String,
        val Type: String,
        val Writer: String,
        val Year: String,
        var imdbID: String,
        val imdbRating: String,
        val imdbVotes: String,
        val totalSeasons: String
) : Parcelable
@Parcelize
    data class Rating(
    val Source: String,
    val Value: String
) : Parcelable
}