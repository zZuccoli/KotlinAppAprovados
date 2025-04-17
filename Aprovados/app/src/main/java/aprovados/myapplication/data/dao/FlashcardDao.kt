package aprovados.myapplication.data.dao

import androidx.room.*
import aprovados.myapplication.data.entities.Flashcard
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashcardDao {
    @Insert
    suspend fun inserir(flashcard: Flashcard)

    @Update
    suspend fun atualizar(flashcard: Flashcard)

    @Delete
    suspend fun deletar(flashcard: Flashcard)

    @Query("SELECT * FROM flashcards WHERE tarefaId = :tarefaId")
    fun listarPorTarefa(tarefaId: Int): Flow<List<Flashcard>>
}
