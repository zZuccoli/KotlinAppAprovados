package aprovados.myapplication.calendario

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import aprovados.myapplication.data.entities.Tarefa
import aprovados.myapplication.databinding.DialogAddTarefaBinding

class TarefaDialog(
    context: Context,
    private val tarefa: Tarefa? = null,
    private val data: String,
    private val onSubmit: (Tarefa) -> Unit
) {
    private val binding = DialogAddTarefaBinding.inflate(LayoutInflater.from(context))

    private val dialog = AlertDialog.Builder(context)
        .setTitle(if (tarefa == null) "Nova Tarefa" else "Editar Tarefa")
        .setView(binding.root)
        .setPositiveButton("Salvar") { _, _ ->
            val titulo = binding.inputTitulo.text.toString().trim()
            val descricao = binding.inputDescricao.text.toString().trim()
            val materia = binding.inputMateria.text.toString().trim()
            val horario = binding.inputHorario.text.toString().trim()

            if (titulo.isEmpty() || horario.isEmpty()) {
                Log.w("TAREFA_DIALOG", "Campos obrigatórios não preenchidos.")
                return@setPositiveButton
            }

            val nova = Tarefa(
                id = tarefa?.id ?: 0,
                titulo = titulo,
                descricao = descricao,
                materia = materia,
                data = data,
                horario = horario
            )

            Log.d("TAREFA_DIALOG", "Tarefa criada: $nova")
            onSubmit(nova)
        }
        .setNegativeButton("Cancelar", null)
        .create()

    fun show() {
        if (tarefa != null) {
            binding.inputTitulo.setText(tarefa.titulo)
            binding.inputDescricao.setText(tarefa.descricao)
            binding.inputMateria.setText(tarefa.materia)
            binding.inputHorario.setText(tarefa.horario)
        }
        dialog.show()
    }
}
