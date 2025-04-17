package aprovados.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import aprovados.myapplication.data.entities.Tarefa
import aprovados.myapplication.data.repository.TarefaRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TarefaViewModel(private val repository: TarefaRepository) : ViewModel() {

    private val _tarefas = MutableStateFlow<List<Tarefa>>(emptyList())
    val tarefas: StateFlow<List<Tarefa>> = _tarefas.asStateFlow()

    fun listarPorData(data: String) {
        viewModelScope.launch {
            repository.listarPorData(data).collect {
                _tarefas.value = it
            }
        }
    }

    fun inserir(tarefa: Tarefa) = viewModelScope.launch {
        Log.d("TAREFA_DEBUG", "Inserindo: ${tarefa.titulo}")
        repository.inserir(tarefa)
    }

    fun atualizar(tarefa: Tarefa) = viewModelScope.launch {
        repository.atualizar(tarefa)
    }

    fun deletar(tarefa: Tarefa) = viewModelScope.launch {
        repository.deletar(tarefa)
    }

}



