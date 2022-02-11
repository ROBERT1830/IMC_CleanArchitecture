package com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.model

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.robertconstantindinescu.imc_cleanarchitecture.feature_calculate_imc.domain.util.Gender

@TypeConverters
class GenderTypeConverter {

    var gson = Gson()

    /**
     * Función que convierte el objeto complejo a string.
     */
    @TypeConverter
    fun genderObjectToString(gender: Gender):String{
        //we use a GSON library to serialize the object that we are passing.
        return gson.toJson(gender) //here we are returning a JSON string. because we use the gson library to convert the object to string


    }

    /**
     * Función que realiza el paso contrario cuadno el objeto se saca de la basd de datos.
     * convertimos el JSON serializado a un objeto complejo.
     */
    @TypeConverter
    fun stringGenderToObject(gender: String): Gender{
        //thi wil convert the string from the database to a Gender object
        val listType = object : TypeToken<Gender>() {}.type
        return gson.fromJson(gender, listType)
    }

}