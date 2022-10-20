package phoenix.org.testprojectsomething.model.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Person::class], version = 1)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun dao(): PersonDao

    companion object {
        @Volatile
        private var INSTANCE: PersonDatabase? = null
        fun getDatabase(context: Context): PersonDatabase {
            var instance: PersonDatabase
            synchronized(this) {
                val converterFactory = DataConverter()
                instance = Room.databaseBuilder(
                    context, PersonDatabase::class.java, "personDatabase"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
            }
            return instance
        }

    }
}