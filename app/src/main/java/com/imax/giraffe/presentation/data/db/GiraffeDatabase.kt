package com.imax.giraffe.presentation.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imax.giraffe.presentation.data.db.entities.Grade
import com.imax.giraffe.presentation.data.db.entities.Listening
import com.imax.giraffe.presentation.data.db.entities.Reading
import com.imax.giraffe.presentation.data.db.entities.Speaking
import com.imax.giraffe.presentation.data.db.entities.TopicOverview
import com.imax.giraffe.presentation.data.db.entities.Vocabulary
import com.imax.giraffe.presentation.data.db.entities.Writing

@Database(
    entities = [Grade::class, Listening::class, Writing::class, Reading::class, Speaking::class, Vocabulary::class, TopicOverview::class],
    exportSchema = false,
    version = 2
)
abstract class GiraffeDatabase : RoomDatabase() {
    abstract fun getLocationDao(): GiraffeDao
}