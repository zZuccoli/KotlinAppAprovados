package aprovados.myapplication.data.repository

import aprovados.myapplication.data.dao.FlashcardDao
import aprovados.myapplication.data.entities.Flashcard
import kotlinx.coroutines.flow.Flow

class FlashcardRepository(private val dao: FlashcardDao) {
    fun listarPorTarefa(tarefaId: Int): Flow<List<Flashcard>> = dao.listarPorTarefa(tarefaId)

    suspend fun inserir(flashcard: Flashcard) = dao.inserir(flashcard)

    suspend fun atualizar(flashcard: Flashcard) = dao.atualizar(flashcard)

    suspend fun deletar(flashcard: Flashcard) = dao.deletar(flashcard)
}