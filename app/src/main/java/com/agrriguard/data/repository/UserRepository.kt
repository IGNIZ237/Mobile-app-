package com.agrriguard.data.repository

import com.agrriguard.data.local.UserDao
import com.agrriguard.data.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun login(email: String, password: String): User? {
        val user = userDao.getUserByEmail(email)
        return if (user?.password == password) user else null
    }

    suspend fun register(user: User) {
        userDao.insertUser(user)
    }
}
