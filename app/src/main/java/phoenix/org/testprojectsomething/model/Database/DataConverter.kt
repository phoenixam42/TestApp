package phoenix.org.testprojectsomething.model.Database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
class DataConverter {

    @TypeConverter
    fun FromListString(list: String): ArrayList<String> {
        val gson = Gson()
        val type = object : TypeToken<String>() {}.type
        return gson.fromJson(list, type)
    }
    @TypeConverter
    fun toStringList (list :ArrayList<String>) :String{
        val gson = Gson()
        val json:String =gson.toJson(list)
        return json
    }
}