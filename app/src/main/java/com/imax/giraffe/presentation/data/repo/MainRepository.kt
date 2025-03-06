package com.imax.giraffe.presentation.data.repo

import com.imax.giraffe.presentation.data.db.GiraffeDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val dao: GiraffeDao
) {

    suspend fun getGrades() = dao.getGrades()

}