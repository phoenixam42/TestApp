package phoenix.org.testprojectsomething.model.Database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import phoenix.org.testprojectsomething.Application.FavoriteMovie

@Dao
interface PersonDao {
    @Query("SELECT * FROM PERSON_DETAIL")
    fun getPersonInfo(): Flow<List<Person>>

    @Query("Select * FROM Person_Detail WHERE name = :name ")
    fun getPersonisedInfo(name: String): Flow<List<Person>>

    @Query("SELECT * FROM Person_Detail ORDER BY name DESC LIMIT 1")
    fun getLastMemberInfo(): Flow<List<Person>>

    @Query("DELETE FROM Person_Detail WHERE favoriteMovie =:favoriteMovie AND name=:username")
    suspend fun deleteBaseOnFavoriteMovie(favoriteMovie:String , username: String)
    @Insert
    suspend fun insert(person: Person)

    @Delete
    suspend fun delete(person: Person)

    @Update
    suspend fun update(person: Person)
}