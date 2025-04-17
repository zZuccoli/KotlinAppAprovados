package aprovados.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import aprovados.myapplication.data.entities.Flashcard
import aprovados.myapplication.data.repository.FlashcardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FlashcardViewModel(private val repository: FlashcardRepository) : ViewModel() {

    private val _flashcards = MutableStateFlow<List<Flashcard>>(emptyList())
    val flashcards: StateFlow<List<Flashcard>> = _flashcards

    fun listarPorTarefa(tarefaId: Int) {
        viewModelScope.launch {
            repository.listarPorTarefa(tarefaId).collect {
                _flashcards.value = it
            }
        }
    }

    fun adicionar(flashcard: Flashcard) = viewModelScope.launch {
        repository.inserir(flashcard)
    }

    fun atualizar(flashcard: Flashcard) = viewModelScope.launch {
        repository.atualizar(flashcard)
    }

    fun deletar(flashcard: Flashcard) = viewModelScope.launch {
        repository.deletar(flashcard)
    }
}
