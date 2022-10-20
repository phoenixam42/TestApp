package phoenix.org.testprojectsomething.Application

import android.app.Application
import phoenix.org.testprojectsomething.Repository
import phoenix.org.testprojectsomething.model.Database.PersonDatabase

class FavoriteMovie : Application() {
    val database by lazy{
        PersonDatabase.getDatabase(this)
    }
    val repository by lazy {
        Repository(database.dao())

    }
}