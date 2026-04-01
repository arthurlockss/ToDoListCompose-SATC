package br.edu.satc.todolistcompose.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TarefaViewModel(private val dao: TarefaDao) : ViewModel() {


    val tarefas: StateFlow<List<Tarefa>> = dao.buscarTodas()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    fun salvarTarefa(titulo: String, descricao: String) {
        viewModelScope.launch {
            val novaTarefa = Tarefa(titulo = titulo, descricao = descricao)
            dao.inserir(novaTarefa)
        }
    }


    fun deletarTarefa(tarefa: Tarefa) {
        viewModelScope.launch {
            dao.deletar(tarefa)
        }
    }

    fun atualizarTarefa(tarefa: Tarefa, concluida: Boolean) {
        viewModelScope.launch {

            dao.atualizar(tarefa.copy(concluida = concluida))
        }
    }
}