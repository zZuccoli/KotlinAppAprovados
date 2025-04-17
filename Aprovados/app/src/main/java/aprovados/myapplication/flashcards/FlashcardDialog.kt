package aprovados.myapplication.flashcards

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import aprovados.myapplication.data.entities.Flashcard
import aprovados.myapplication.databinding.DialogFlashcardBinding

class FlashcardDialog(
    context: Context,
    private val flashcard: Flashcard? = null,
    private val tarefaId: Int,
    private val onSubmit: (Flashcard) -> Unit
) {
    private val binding = DialogFlashcardBinding.inflate(LayoutInflater.from(context))

    private val dialog = AlertDialog.Builder(context)
        .setTitle(if (flashcard == null) "Novo Flashcard" else "Editar Flashcard")
        .setView(binding.root)
        .setPositiveButton("Salvar") { _, _ ->
            val novo = Flashcard(
                id = flashcard?.id ?: 0,
                tarefaId = tarefaId,
                pergunta = binding.inputPergunta.text.toString(),
                resposta = binding.inputResposta.text.toString()
            )
            onSubmit(novo)
        }
        .setNegativeButton("Cancelar", null)
        .create()

    init {
        flashcard?.let {
            binding.inputPergunta.setText(it.pergunta)
            binding.inputResposta.setText(it.resposta)
        }
    }

    fun show() = dialog.show()
}
