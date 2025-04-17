package aprovados.myapplication.data.repository

import aprovados.myapplication.data.dao.TarefaDao
import aprovados.myapplication.data.entities.Tarefa

class TarefaRepository(private val dao: TarefaDao) {

    fun listarPorData(data: String) = dao.listarPorData(data)

    suspend fun inserir(tarefa: Tarefa) = dao.inserir(tarefa)

    suspend fun atualizar(tarefa: Tarefa) = dao.atualizar(tarefa)

    suspend fun deletar(tarefa: Tarefa) = dao.deletar(tarefa)
}
