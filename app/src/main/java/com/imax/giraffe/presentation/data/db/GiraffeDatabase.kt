package com.imax.giraffe.presentation.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imax.giraffe.presentation.data.db.entities.Grade

@Database(entities = [Grade::class], exportSchema = false, version = 1)
abstract class GiraffeDatabase : RoomDatabase() {
    abstract fun getLocationDao(): GiraffeDao
}