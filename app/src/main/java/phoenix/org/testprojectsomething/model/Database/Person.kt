package phoenix.org.testprojectsomething.model.Database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Person_Detail")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "" ,
    val favoriteMovie : String
) : Parcelable
