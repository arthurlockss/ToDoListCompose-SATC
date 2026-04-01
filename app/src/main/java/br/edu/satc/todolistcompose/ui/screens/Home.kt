@file:OptIn(ExperimentalMaterial3Api::class)

package br.edu.satc.todolistcompose.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.edu.satc.todolistcompose.data.Tarefa
import br.edu.satc.todolistcompose.data.TarefaViewModel
import br.edu.satc.todolistcompose.data.TaskData
import br.edu.satc.todolistcompose.ui.components.TaskCard
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: TarefaViewModel,
    isDarkMode: Boolean,
    onThemeToggle: () -> Unit
) {
    val tarefas by viewModel.tarefas.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        IconButton(
            onClick = onThemeToggle,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 8.dp)
        ) {
            Icon(
                imageVector = if (isDarkMode) Icons.Filled.WbSunny else Icons.Filled.NightsStay,
                contentDescription = "Trocar Tema"
            )
        }

        Content(tarefas = tarefas, viewModel = viewModel)

        NewTask(viewModel = viewModel)
    }
}

@Composable
fun Content(tarefas: List<Tarefa>, viewModel: TarefaViewModel) {
    LazyColumn(
        modifier = Modifier.padding(top = 48.dp)
    ) {
        items(items = tarefas) { tarefa ->
            val taskData = TaskData(
                id = tarefa.id,
                title = tarefa.titulo,
                description = tarefa.descricao,
                complete = tarefa.concluida
            )


            TaskCard(
                taskData = taskData,
                onTaskCheckedChange = { isChecked ->
                    viewModel.atualizarTarefa(tarefa, isChecked)
                }
            )
        }
    }
}

@Composable
fun NewTask(viewModel: TarefaViewModel) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ExtendedFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            text = { Text("Nova tarefa") },
            icon = { Icon(Icons.Filled.Add, contentDescription = "") },
            onClick = {
                showBottomSheet = true
            })
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = taskTitle,
                    onValueChange = { taskTitle = it },
                    label = { Text(text = "Título da tarefa") })
                OutlinedTextField(
                    value = taskDescription,
                    onValueChange = { taskDescription = it },
                    label = { Text(text = "Descrição da tarefa") })
                Button(
                    modifier = Modifier.padding(top = 4.dp),
                    onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }

                        if (taskTitle.isNotEmpty()) {
                            viewModel.salvarTarefa(taskTitle, taskDescription)
                        }

                        taskTitle = ""
                        taskDescription = ""

                    }) {
                    Text("Salvar")
                }
            }
        }
    }
}