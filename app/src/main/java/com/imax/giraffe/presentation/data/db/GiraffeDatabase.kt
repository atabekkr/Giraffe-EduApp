package com.imax.giraffe.presentation.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imax.giraffe.presentation.data.db.entities.Grade
import com.imax.giraffe.presentation.data.db.entities.Listening

@Database(entities = [Grade::class, Listening::class], exportSchema = false, version = 1)
abstract class GiraffeDatabase : RoomDatabase() {
    abstract fun getLocationDao(): GiraffeDao
}