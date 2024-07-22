package com.arzhang.borutoapp.data.local

import androidx.room.TypeConverter

class DatabaseConvertor {

    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>) : String {
        val stringBuilder = StringBuilder()
        for(item in list) {
            stringBuilder.append(item).append(separator)
        }

        //remove the last comma
        stringBuilder.setLength(stringBuilder.length - separator.length)
        return stringBuilder.toString()
    }
    @TypeConverter
    fun convertStringToList(stringList: String) : List<String> {
        return stringList.split(separator)
    }


}