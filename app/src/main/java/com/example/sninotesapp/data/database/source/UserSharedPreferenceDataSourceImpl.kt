package com.example.sninotesapp.data.database.source

import android.content.Context
import com.example.sninotesapp.domain.model.User

class UserSharedPreferenceDataSourceImpl(
    private val context: Context
): UserSharedPreferenceDataSource {

    private val sharedPreferences =
        context.getSharedPreferences("AuthData", Context.MODE_PRIVATE)

    override fun putUserData(user: User) {
        sharedPreferences.edit().putString("session", user.session).apply()
        sharedPreferences.edit().putString("login",user.login).apply()
        sharedPreferences.edit().putString("password",user.password).apply()
        sharedPreferences.edit().putString("name",user.name).apply()
    }

    override fun getUserData(): User? {
        val session = sharedPreferences.getString("session",null)
        val login = sharedPreferences.getString("login",null)
        val password = sharedPreferences.getString("password",null)
        val name = sharedPreferences.getString("name",null)
        return if(login == null) null else {
            User(
                login = login,
                session = session!!,
                password = password!!,
                name = name!!
            )
        }
    }

    override fun deleteUser() {
        sharedPreferences.edit().remove("session").apply()
        sharedPreferences.edit().remove("login").apply()
        sharedPreferences.edit().remove("password").apply()
        sharedPreferences.edit().remove("name").apply()
    }


}