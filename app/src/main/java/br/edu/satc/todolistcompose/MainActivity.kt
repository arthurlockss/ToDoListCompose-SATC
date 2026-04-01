package br.edu.satc.todolistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.room.Room
import br.edu.satc.todolistcompose.data.AppDatabase
import br.edu.satc.todolistcompose.data.TarefaViewModel
import br.edu.satc.todolistcompose.data.ThemePreferences
import br.edu.satc.todolistcompose.ui.screens.HomeScreen
import br.edu.satc.todolistcompose.ui.theme.ToDoListComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "todo-db").build()
        val viewModel = TarefaViewModel(db.tarefaDao())


        val themePrefs = ThemePreferences(this)

        setContent {
            var isDarkMode by remember { mutableStateOf(themePrefs.isDarkMode()) }


            ToDoListComposeTheme(darkTheme = isDarkMode) {
                HomeScreen(
                    viewModel = viewModel,
                    isDarkMode = isDarkMode,
                    onThemeToggle = {

                        isDarkMode = !isDarkMode
                        themePrefs.setDarkMode(isDarkMode)
                    }
                )
            }
        }
    }
}