package br.edu.satc.todolistcompose.data

import androidx.room.*
import br.edu.satc.todolistcompose.data.Tarefa
import kotlinx.coroutines.flow.Flow

@Dao
interface TarefaDao {
    @Query("SELECT * FROM tabela_tarefas")
    fun buscarTodas(): Flow<List<Tarefa>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(tarefa: Tarefa)

    @Delete
    suspend fun deletar(tarefa: Tarefa)

    @Update
    suspend fun atualizar(tarefa: Tarefa)
}