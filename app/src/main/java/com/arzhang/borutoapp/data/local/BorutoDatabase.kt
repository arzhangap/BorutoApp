package com.arzhang.borutoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arzhang.borutoapp.data.local.dao.HeroDao
import com.arzhang.borutoapp.data.local.dao.HeroRemoteKeyDao
import com.arzhang.borutoapp.domain.model.Hero
import com.arzhang.borutoapp.domain.model.HeroRemoteKey

@Database(entities = [Hero::class,HeroRemoteKey::class], version = 1)
@TypeConverters(DatabaseConvertor::class)
abstract class BorutoDatabase : RoomDatabase() {

    abstract fun heroDao() : HeroDao
    abstract fun heroRemoteKeyDao() : HeroRemoteKeyDao

}