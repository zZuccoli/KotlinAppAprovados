package aprovados.myapplication.data.dao

import androidx.room.*
import aprovados.myapplication.data.entities.Tarefa
import kotlinx.coroutines.flow.Flow

@Dao
interface TarefaDao {

    @Query("SELECT * FROM tarefas WHERE data = :data")
    fun listarPorData(data: String): Flow<List<Tarefa>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(tarefa: Tarefa)

    @Update
    suspend fun atualizar(tarefa: Tarefa)

    @Delete
    suspend fun deletar(tarefa: Tarefa)
}
