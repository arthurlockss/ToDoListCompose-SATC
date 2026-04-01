package br.edu.satc.todolistcompose.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabela_tarefas")
data class Tarefa(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo: String,
    val descricao: String,
    val concluida: Boolean = false
)