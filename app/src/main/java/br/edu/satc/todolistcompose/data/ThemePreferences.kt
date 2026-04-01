package br.edu.satc.todolistcompose.data

import android.content.Context

class ThemePreferences(context: Context) {
    private val sharedPrefs = context.getSharedPreferences("config_prefs", Context.MODE_PRIVATE)

    fun setDarkMode(isDark: Boolean) {
        sharedPrefs.edit().putBoolean("dark_mode", isDark).apply()
    }

    fun isDarkMode(): Boolean {
        return sharedPrefs.getBoolean("dark_mode", false)
    }
}